package computerdatabase

import java.util.UUID

import scala.language.postfixOps

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._


class CarSimulation extends Simulation {





  def SOAP_REQUEST="""<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:cook="http://informatique.polytech.unice.fr/soa1/cookbook/">
   <soapenv:Header/>
   <soapenv:Body>
      <cook:getCarsByPreferences>
         <!--Optional:-->
         <place>Londre</place>
         <!--Optional:-->
         <dateStart>2017-08-23</dateStart>
         <!--Optional:-->
         <dateEnd>2017-08-12</dateEnd>
      </cook:getCarsByPreferences>
   </soapenv:Body>
</soapenv:Envelope>"""





  val httpConf =
    http
      .baseURL("http://localhost:9020/car-rpc/ExternalCarRentalService")

  val stressSample =
    scenario("find Car")
        .repeat(10)
        {
          exec(http("find_Car_request")
            .post("http://localhost:9020/car-rpc/ExternalCarRentalService")
            //.headers(headers_1)
            .body(StringBody(SOAP_REQUEST))
            .check(status.is(200))
          )
          
        }


setUp(stressSample.inject(rampUsers(20) over (10 seconds)))
  //setUp(stressSample.inject(rampUsers(20) over (10 seconds)).protocols(httpConf))
}