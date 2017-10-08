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


exports.approveTravel = function(req,res){
  var id = req.params.id;
  console.log(id);
  BusinessTravel.findById(id,function(err,businessTravel){
      if(err)
        res.send(err);
      console.log(businessTravel);
      businessTravel.approved = true;
      businessTravel.save(function(err,updatedBusinessTravel){
         if(err)
            res.send(err);
          res.json({'message': 'business travel updated'});
      });
  });
}


exports.getAllTravels = function(req,res){
  BusinessTravel.find({}, function(err, businessTravels) {
    if(err)
      res.send(err);
    res.send({'businessTravels':businessTravels});  
  });
}