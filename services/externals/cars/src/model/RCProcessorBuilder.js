import RCProcessor from "./RCProcessor";

export default class RCProcessorBuilder {

  constructor() {
    this._rcProcessor = new RCProcessor();
  }

  _rcProcessor: ?RCProcessor = null;

  withLocation(location: any) {
    if (typeof location !== 'string' || location.length === 0) {
      return this;
    }
    this._rcProcessor.addMatchingFunction(rentalCar => {
      const fullAddress =
        rentalCar.agency.address + ' ' +
        rentalCar.agency.city + ' ' +
        rentalCar.agency.country;
      return fullAddress.toLowerCase().indexOf(location.toLowerCase()) !== -1;
    });
    return this;
  }

  withTimeframe(start: any, end: any) {
    if (start === null || isNaN(new Date(start)) || end === null || isNaN(new Date(end))) {
      return this;
    }
    const startSafe = new Date(start);
    const endSafe = new Date(start);
    this._rcProcessor.addMatchingFunction(car => {
      return car.bookings.some(booking => {
        const bookingStart = new Date(booking.start);
        const bookingEnd = new Date(booking.end);
        return endSafe < bookingStart || startSafe > bookingEnd;
      });
    });
    return this;
  }

  withSortOrder(sortOrder: any) {
    if (typeof sortOrder !== 'string' || (sortOrder.toLowerCase() !== 'asc' && sortOrder.toLowerCase() !== 'desc')) {
      return this;
    }
    if (sortOrder === 'asc')
      this._rcProcessor.setSortingFunction((carA, carB) => carA.priceperday - carB.priceperday);
    else if (sortOrder === 'desc')
      this._rcProcessor.setSortingFunction((carA, carB) => carB.priceperday - carA.priceperday);
    return this;
  }

  build() {
    return this._rcProcessor;
  }

}