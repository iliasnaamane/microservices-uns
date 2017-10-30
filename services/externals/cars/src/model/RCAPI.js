import cars from '../../assets/cars.json';

export default class RCAPI {

  async getAll() {
    return cars;
  }

}