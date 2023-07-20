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

    String mainCommand = "mvn -P gatling test-compile gatling:test -Dgatling.simulationClass=app.perf.job.TodoLongSimulation";
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
        Command.exec(true, null, new String[]{"mvn", "-P", "gatling", "exec:java", "-Dexec.classpathScope=test",
            "-Dexec.mainClass=io.gatling.app.Gatling", "-Dexec.args=-ro " + reportDir + " -rf " + buildDir});
    }

    // ./mvnw exec:java -Dexec.mainClass=app.App -Dexec.args=classpath:app
    // export URL_BASE=http://host.docker.internal:8080
    // mvn test-compile exec:java -Dexec.classpathScope=test -Dexec.mainClass=app.perf.job.TodoPerfJobConfig -Dkarate.env=perf -Dexec.args=5
    // docker run -it --rm -p 8090:8090 -e URL_BASE=http://host.docker.internal:8080 -w /karate-todo karate-mvn mvn test-compile exec:java -Dexec.classpathScope=test -Dexec.mainClass=app.perf.job.TodoPerfJobConfig -Dkarate.env=perf -Dexec.args=5
    // docker run -it --rm karate-mvn java -jar karate.jar -j http://host.docker.internal:8090   
    // ./mvnw exec:java -Dexec.classpathScope=test -Dexec.mainClass=com.intuit.karate.Main -Dexec.args="-j http://localhost:8090"
    public static void main(String[] args) {
        int count = 5;
        if (args.length > 0) {
            try {
                count = Integer.valueOf(args[0]);
            } catch (Exception e) {
                // ignore
            }
        }
        System.out.println("using worker node count: " + count);
        TodoPerfJobConfig config = new TodoPerfJobConfig(count, "localhost", 8090);
        JobManager manager = new JobManager(config);
        manager.start();
        manager.waitForCompletion();
        manager.server.stop();
        logger.debug("waiting for server shutdown ...");
    }

}
