'use strict';

var mongoose = require('mongoose'),
BusinessTravel = mongoose.model('BusinessTravel');

exports.submitTravel     = function(req,res){
  var businessTravel = new BusinessTravel(req.body);
  businessTravel.save(function(err,task){
     if(err)
        res.send(err);
    res.json({'message':'business travel sumbitted successfully and waiting for approval'});
  });
};
