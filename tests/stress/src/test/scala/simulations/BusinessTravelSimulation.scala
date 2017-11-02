package computerdatabase

import java.util.UUID

import scala.language.postfixOps

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._


class BusinessTravelSimulation extends Simulation {





  def JSON_REQUEST="""{
 "id": 1,
 "email": "ilias.naamane1@gmail.com",
 "destination": "Rabat",
 "flights":[
   {
     "id_flight":"3",
     "departDate":"2016-06-13",
     "returnDate":"2016-06-15",
     "type":"one way",
     "from": "Casa",
     "to": "Rabat",
     "price": 150
   },
   {
     "id_flight":"5",
     "departDate":"2016-06-13",
     "returnDate":"2016-06-15",
     "type":"one way",
     "from": "Casa",
     "to": "Rabat",
     "price": 150
   }
 ],

 "carRentals":[
   {
     "id_rentcar":"4",
     "startDate":"2016-07-03",
     "endDate":"2016-07-23"

   },
   {
     "id_rentcar":"6",
     "startDate":"2016-07-03",
     "endDate":"2016-07-23"

   }

   ],

  "hotels":[
    {
      "id_hotel":"5",
      "nights": 3,
      "price": 120
    },

    {
      "id_hotel":"7",
      "nights": 3,
      "price": 120
    }

  ],
  "otherTickets":[
         {
           "id_ticket": "3",
           "description": "Ticket de restaurant",
           "price": "1200"
         },
         {
           "id_ticket": "5",
           "description": "Ticket de restaurant",
           "price": "1200"
         }

    ]
}"""




  val httpConf =
    http
      .baseURL("http://localhost:9040/")
      //.acceptHeader("application/xml")
      .header("Content-Type", "application/json")

  val stressSample =
    scenario("business")
        .repeat(10)
        {
          exec(http("busyness")
            .post("travels")
            .body(StringBody(JSON_REQUEST))
            .check(status.is(200))
          )
          
        }


setUp(stressSample.inject(rampUsers(20) over (10 seconds))).protocols(httpConf)
  //setUp(stressSample.inject(rampUsers(20) over (10 seconds)).protocols(httpConf))
}