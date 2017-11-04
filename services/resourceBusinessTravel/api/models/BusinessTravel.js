'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var BusinessTravelSchema = new Schema({
  email: String,
  destination: String,
  start:Date,
  end:Date,
  approved: {
    type:Boolean,
    default: false
  },
  flight:
    {
      id_flight: String,
      price: Number
    },
  

  carRental:
    {
      id_rentcar: String,
      modele: String,
      marque: String,
      price: Number

    },

   hotel:
   {
      id_hotel:String,
      hotel_name: String,
      price: Number
    }

   



});

module.exports = mongoose.model('BusinessTravel',BusinessTravelSchema);
