package executor


import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._


class scenario2 extends Simulation
{

  var httpConfig = http.baseUrl("https://reqres.in")
    .header("accept",value = "application/json")
    .header("content-type",value = "application/json")


  var scenarioRunner = scenario("Get Endpoint")

    .exec(http("Get request").get("/api/users/2")
      .check(status is 200)
    )


  setUp(scenarioRunner.inject(atOnceUsers(5))).protocols(httpConfig)

}