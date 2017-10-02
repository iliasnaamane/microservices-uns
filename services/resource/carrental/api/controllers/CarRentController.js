  'use strict';

  var mongoose = require('mongoose'),
  CarRent = mongoose.model('CarRent');
 // create new car rental
  exports.store     = function(req,res){
    var carRent = new CarRent(req.body);
    carRent.save(function(err,task){
       if(err)
          res.send(err);
      res.json({'message':'car rent have been added successfully'});
    });
  };


  // Get alternatives car rentals for destination

  exports.index = function(req,res){
    var destination = req.query.destination;
    var sort = req.query.sort;
    var fromDate = req.query.fromDate;
    var toDate =req.query.toDate;
    if(!destination){
      res.status(400);
      res.json("{please enter destination in parameter");
    }
    // to find the available cars for a given fromDate and toDate
    CarRent.aggregate([{
     $project: {
         model: 1,
         brand:1,
         price:1,
         rents: {
              $filter: {
                      input: '$rents',
                       as: 'rent',
                      cond: {
                          $or: [
                              { $and: [ { $lt: ["$$rent.fromDate", new Date(fromDate) ] } ,  { $lt: ["$$rent.toDate", new Date(fromDate) ]} ] },
                              { $and: [ { $gt: ["$$rent.fromDate", new Date(toDate) ] } ,  { $gt: ["$$rent.toDate", new Date(toDate) ]} ] }

                          ]
                      }

              }
          }

     }

    }
  ]).exec(function(err, carrents){
          if(err){
            res.send(err)
          }
          else{
            var key,cars=[] ;
            for(key in carrents) {
               if(carrents[key].rents.length != 0){
                 cars.push(carrents[key]);
               }
               continue;
             }

           }
           if(!carrents){
              res.status(204);
              res.json({'message':'No cars are available for this given duration'});
           }
           else{
             res.json(cars);
           }
    });


  // Update car rental

}
