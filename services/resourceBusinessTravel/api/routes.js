'use strict';


module.exports = function(app){
  var BusinessTravelController = require('./controllers/BusinessTravelController');


  // Submit business travel feature
  app.route('/travels')
     .post(BusinessTravelController.submitTravel)
     .get(BusinessTravelController.getAllTravels);
  app.route('/travel/:id?')
    .put(BusinessTravelController.approveTravel);
};
