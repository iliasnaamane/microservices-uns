## Business Travel 

### Mongoose Schema
```javascript
var BusinessTravelSchema = new Schema({
  email: String,
  destination: String,
  approved: {
    type:Boolean,
    default: false
  },
  flights:[
    {
      id_flight:String,
      departDate:Date,
      returnDate:Date,
      type_flight:String,
      from: String,
      to: String,
      price: Number
    },
  ],

  carRentals:[
    {
      id_rentcar:String,
      startDate:Date,
      endDate:Date,

    }],

   hotels:[
     {
       id_hotel:String,
       nights: Number,
       price: Number
     }

   ],
   otherTickets:[
     {
       id_ticket: String,
       description: String,
       price: Number
     }
   ]



});

  ```  

### Submit Business Travel via POST REQUEST ( http://localhost:9040/travels )
#### Body of the request

	
	```json

	  {

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
	}


	```	
#### Response of the request : 
Status 200 OK -> with message: 
```json
{
    "message": "business travel sumbitted successfully and waiting for approval"
}
```

	
### Approve Business Travel via PUT REQUEST ( http://localhost:9040/travel/:travelId )
#### Response
```json
{
    "message": "business travel updated"
}
```



### Get All Business Travels (http://localhost:9040/travels )


