package executor

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._


class scenario1 extends Simulation
{

  var httpConfig = http.baseUrl("https://gorest.co.in")
    .header("Authorization",value = "Bearer Get Token form gorest.co.in")
    .header("accept",value = "application/json")
    .header("content-type",value = "application/json")


  var scenarioRunner = scenario("End To End")

    .exec(http("Create Account").post("/public/v2/users")
      .body(RawFileBody(".\\src\\test\\resources\\request.json"))
      .check(jsonPath("$..id").saveAs("userId")))
    .exec(http("Get User").get("/public/v2/users/${userId}")
    .check( status is 200))
    .exec(http("Delete User").delete("/public/v2/users/${userId}")
      .check(status is 204))

  setUp(scenarioRunner.inject(atOnceUsers(1))).protocols(httpConfig)

}