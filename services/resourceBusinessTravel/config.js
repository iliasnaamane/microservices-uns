module.exports = {
  db:{
      production: "mongodb://"+process.env.MONGODB_ADDRESS+":27017/BusinessTravelDB",
      development: "mongodb://"+process.env.MONGODB_ADDRESS+":27017/BusinessTravelDB",
    }

};
