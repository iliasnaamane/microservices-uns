'use strict';


module.exports = function(app){
  var CarRentController = require('./controllers/CarRentController');


  // Get all by parameters
  app.route('/carrents')
    .get(CarRentController.index);

  // Create
  app.route('/carrents')
     .post(CarRentController.store);
  // Read
  /*app.route('/carrent/:id')
      .get(CarRentController.show);
  // Update
  app.route('/carrent/:id')
      .put(CarRentController.update);
  app.route('/carrent/:id')
    .delete(CarRentController.delete);*/




};
