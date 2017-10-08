package computerdatabase

import java.util.UUID

import scala.language.postfixOps

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._


class VolsSimulation extends Simulation {





  def JSON_REQUEST="""{
    "event": "Return_Duration",
    "Outbound_date": "12-10-2017",
    "return_date":"13-10-2017",
    "from" :"Nice",
    "to" : "Paris"
}"""




  val httpConf =
    http
      .baseURL("http://localhost:9030/vols/webapi/")
      //.acceptHeader("application/xml")
      .header("Content-Type", "application/json")

  val stressSample =
    scenario("find Vols")
        .repeat(10)
        {
          exec(http("vols")
            .post("vols")
            .body(StringBody(JSON_REQUEST))
            .check(status.is(200))
          )
          
        }


setUp(stressSample.inject(rampUsers(20) over (10 seconds))).protocols(httpConf)
  //setUp(stressSample.inject(rampUsers(20) over (10 seconds)).protocols(httpConf))
}