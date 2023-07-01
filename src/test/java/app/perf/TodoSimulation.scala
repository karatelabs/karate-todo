package app.perf

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._
import scala.concurrent.duration._

class TodoSimulation extends Simulation {

  Utils.startServer()

  val protocol = karateProtocol(
    "/api/todos/{id}" -> Nil
  )

  val main = scenario("main").exec(karateFeature("classpath:app/api/simple/simple.feature"))

  setUp(
    main.inject(rampUsers(10) during (5 seconds)).protocols(protocol)
  )

}