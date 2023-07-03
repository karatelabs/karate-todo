package app.perf.job;

import com.intuit.karate.Constants;
import com.intuit.karate.FileUtils;
import com.intuit.karate.StringUtils;
import com.intuit.karate.job.JobChunk;
import com.intuit.karate.job.JobCommand;
import com.intuit.karate.job.JobConfigBase;
import com.intuit.karate.job.JobManager;
import com.intuit.karate.job.JobUtils;
import com.intuit.karate.shell.Command;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TodoPerfJobConfig extends JobConfigBase<Integer> {

    String mainCommand = "mvn test -P gatling";
    String buildDir = FileUtils.getBuildDir();
    String reportDir = Constants.KARATE_REPORTS;
    String executorDir = buildDir + File.separator + "gatling";

    public TodoPerfJobConfig(int executorCount, String host, int port) {
        super(executorCount, host, port);
        addEnvPropKey("URL_BASE");
    }

    @Override
    public String getExecutorCommand(String jobId, String jobUrl, int index) {
        return null;
    }    

    @Override
    public List<JobCommand> getStartupCommands() {
        return Collections.EMPTY_LIST;
    }        

    @Override
    public List<JobCommand> getShutdownCommands() {
        return Collections.EMPTY_LIST;
    }        
    
    @Override
    public List<Integer> getInitialChunks() {
        int count = getExecutorCount();
        if (count < 1) {
            throw new RuntimeException("executor count should be greater than zero");
        }
        List<Integer> list = new ArrayList(count);
        for (int i = 0; i < count; i++) {
            list.add(i);
        }
        return list;
    }
    
    @Override
    public String getExecutorDir() {
        return executorDir;
    }

    @Override
    public Integer handleUpload(JobChunk<Integer> jc, File upload) {
        String karateLog = upload.getPath() + File.separator + "karate.log";
        File karateLogFile = new File(karateLog);
        if (karateLogFile.exists()) {
            karateLogFile.renameTo(new File(karateLog + ".txt"));
        }
        String gatlingReportDir = buildDir + File.separator + reportDir;
        new File(gatlingReportDir).mkdirs();
        File[] dirs = upload.listFiles();
        for (File dir : dirs) {
            if (dir.isDirectory()) {
                File file = JobUtils.getFirstFileMatching(dir, n -> n.endsWith("simulation.log"));
                if (file != null) {
                    FileUtils.copy(file, new File(gatlingReportDir + File.separator + "simulation_" + jc.getId() + ".log"));
                }
            }
        }
        return jc.getValue();
    }

    @Override
    public List<JobCommand> getMainCommands(JobChunk jc) {
        String temp = mainCommand;
        for (String k : sysPropKeys) {
            String v = StringUtils.trimToEmpty(System.getProperty(k));
            if (!v.isEmpty()) {
                temp = temp + " -D" + k + "=" + v;
            }
        }
        return Collections.singletonList(new JobCommand(temp));
    }

    @Override
    public void onStop() {
        super.onStop();
        Command.exec(true, null, new String[]{"./mvnw", "-P", "gatling", "exec:java", "-Dexec.classpathScope=test",
            "-Dexec.mainClass=io.gatling.app.Gatling", "-Dexec.args=-ro " + reportDir + " -rf " + buildDir});
    }
    
    // mvn test-compile exec:java -Dexec.classpathScope=test -Dexec.mainClass=app.perf.job.TodoPerfJobConfig -Dkarate.env=perf
    // docker run -it --rm -v "$HOME/.m2":/root/.m2 karate-mvn java -jar karate.jar -j http://host.docker.internal:8090
    public static void main(String[] args) {
        TodoPerfJobConfig config = new TodoPerfJobConfig(5, "localhost", 8090);        
        JobManager manager = new JobManager(config);       
        manager.start();
        manager.waitForCompletion();
        manager.server.stop();
        logger.debug("waiting for server shutdown ...");        
    }

}
