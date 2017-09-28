var express = require('express'),
app = express(),
port = process.env.PORT || 3000,
mongoose = require('mongoose'),
BusinessTravel = require('./api/models/BusinessTravel'),
bodyParser = require('body-parser');

//mongoose instance connection url connection
mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost/BusinessTravelDB',{ useMongoClient: true });

app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());

var routes = require('./api/routes');
routes(app);


app.listen(port);

console.log('RESTful API server started on: ' + port);
