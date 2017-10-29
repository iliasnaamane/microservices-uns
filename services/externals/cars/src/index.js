import 'babel-polyfill';
import restify from 'restify';
import logger from 'morgan';
import status from '../assets/status.json';

const server = restify.createServer({
  name: status.name,
  version: status.version
});

server.use(logger('dev'));
server.use(restify.plugins.acceptParser(server.acceptable));
server.use(restify.plugins.queryParser());
server.use(restify.plugins.bodyParser());

// Answer to HTTP ping
server.get('/', require('./routes/get').default);

// Rental cars search service implementation
server.get('/cars', require('./routes/cars/get').default);

server.listen(9060, () => {
  console.log('%s listening at %s', server.name, server.url);
});
