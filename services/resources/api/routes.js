'use strict';


module.exports = function(app){
  var BusinessTravelController = require('./controllers/BusinessTravelController');


  // Submit business travel feature
  app.route('/api/v1/travel')
     .post(BusinessTravelController.submitTravel);




};
