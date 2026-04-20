package app.perf;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.karatelabs.gatling.KarateProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.karatelabs.gatling.KarateDsl.*;

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
