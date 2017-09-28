'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var BusinessTravelSchema = new Schema({
  id: Number,
  email: String,
  destination: String,
  flights:[
    {
      id_flight:Number,
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
      id_rentcar:Number,
      startDate:Date,
      endDate:Date,

    }],

   hotels:[
     {
       id_hotel:Number,
       nights: Number,
       price: Number
     }

   ],
   otherTickets:[
     {
       id_ticket: Number,
       description: String,
       price: Number
     }
   ]



});

module.exports = mongoose.model('BusinessTravel',BusinessTravelSchema);
