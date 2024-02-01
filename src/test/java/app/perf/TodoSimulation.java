package app.perf;

import com.intuit.karate.gatling.javaapi.KarateProtocolBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static com.intuit.karate.gatling.javaapi.KarateDsl.*;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;

public class TodoSimulation extends Simulation {

    public TodoSimulation() {

        KarateProtocolBuilder protocol = karateProtocol(
                uri("/api/todos/{id}").nil()
        );

        ScenarioBuilder main = scenario("main").exec(karateFeature("classpath:app/api/simple/simple.feature"));

        setUp(
                main.injectOpen(rampUsers(10).during(5)).protocols(protocol)
        );
    }

}
