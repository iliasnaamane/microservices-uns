package computerdatabase

import java.util.UUID

import scala.language.postfixOps

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._


class HotelSimulation extends Simulation {





  def SOAP_REQUEST="""<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:cook="http://informatique.polytech.unice.fr/soa1/cookbook/">
   <soapenv:Header/>
   <soapenv:Body>
      <cook:recherche>
         <!--Optional:-->
         <lieu>"Londre"</lieu>
         <dure>2</dure>
         <arg2>0</arg2>
      </cook:recherche>
   </soapenv:Body>
</soapenv:Envelope>"""
//<arg2>0</arg2>




  val httpConf =
    http
      .baseURL("http://localhost:9010/hotel-rpc/")
      //.acceptHeader("application/xml")
      .header("Content-Type", "application/xml")

  val stressSample =
    scenario("find Hotel")
        .repeat(10)
        {
          exec(http("recherche")
            .post("ExternalHotelFinderService")
            .body(StringBody(SOAP_REQUEST))
            .check(status.is(200))
          )
          
        }


setUp(stressSample.inject(rampUsers(20) over (10 seconds))).protocols(httpConf)
  //setUp(stressSample.inject(rampUsers(20) over (10 seconds)).protocols(httpConf))
}