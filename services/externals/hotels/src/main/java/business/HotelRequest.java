package business;

import java.util.Date;
import java.util.Optional;

/**
 * @author Antoine Aubé <aube.antoine@gmail.com>
 */
public class HotelRequest {

  private String city;
  private Date availability;
  private Boolean costOrder;

  public HotelRequest(String city, Date availability, Boolean costOrder) {
    this.city = city;
    this.availability = availability;
    this.costOrder = costOrder;
  }

  public Optional<String> getCity() {
    return Optional.ofNullable(city);
  }

  public Optional<Date> getAvailability() {
    return Optional.ofNullable(availability);
  }

  public Optional<Boolean> orderByAscendingPrice() {
    return Optional.ofNullable(costOrder);
  }
}
