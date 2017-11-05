import RCProcessorBuilder from "../../model/RCProcessorBuilder";
import RCProcessor from "../../model/RCProcessor";
import RCAPI from "../../model/RCAPI";

export default async (req, res, next) => {
  let cars: ?Array<*> = null;
  try {
    let rcProcessor: RCProcessor = new RCProcessorBuilder()
      .withLocation(req.query.location)
      .withTimeframe(req.query.start, req.query.end)
      .withSortOrder(req.query.sort)
      .build();
    let api: RCAPI = new RCAPI();
    cars = rcProcessor.process(await api.getAll());
  } catch (error) {
    res.send(400, error);
    return next();
  }
  res.send(cars);
  return next();
}
