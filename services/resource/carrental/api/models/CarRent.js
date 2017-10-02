'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var CarRentSchema = new Schema({
  id: Number,
  model: String,
  brand: String,
  price: Number,
  rents:[
    {
      id_rent:Number,
      fromDate:Date,
      toDate:Date
    }
  ]



});

module.exports = mongoose.model('CarRent',CarRentSchema);
