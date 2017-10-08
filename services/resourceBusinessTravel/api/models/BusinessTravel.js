'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

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

module.exports = mongoose.model('BusinessTravel',BusinessTravelSchema);
