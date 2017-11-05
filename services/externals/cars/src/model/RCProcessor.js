export default class RCProcessor {

  _matchingFuncs = [];
  _sortingFunc = _ => 0;

  addMatchingFunction(matchingFunc) {
    this._matchingFuncs.push(matchingFunc);
  }

  setSortingFunction(sortingFunction) {
    this._sortingFunc = sortingFunction;
  }

  process(cars: Array<*>) {
    return cars.filter(car => {
      for (let matchingFunc of this._matchingFuncs) {
        if (!matchingFunc(car)) return false;
      }
      return true;
    });
  }

}