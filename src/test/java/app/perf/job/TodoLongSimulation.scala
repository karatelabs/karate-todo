package app.perf.job

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class TodoLongSimulation extends Simulation {

  val protocol = karateProtocol(
    "/api/todos/{id}" -> Nil
  )

  val main = scenario("main").during(10.minutes) { 
    pace(10).exec(karateFeature("classpath:app/api/simple/simple.feature")) 
  }

  val reset = scenario("reset").during(10.minutes) {
    pace(30).exec(karateFeature("classpath:app/api/simple/reset.feature"))
  }

  setUp(
    main.inject(rampUsers(10) during (5 seconds)).protocols(protocol),
    reset.inject(atOnceUsers(1)).protocols(protocol)
  )

}