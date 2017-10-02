module.exports = {
  db:{
      production: "mongodb://"+process.env.MONGODB_ADDRESS+":27017/CarRentDB",
      development: "mongodb://"+process.env.MONGODB_ADDRESS+":27017/CarRentDB",
    }

};
