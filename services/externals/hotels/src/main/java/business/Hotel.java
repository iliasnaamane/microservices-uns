package business;

/**
 * @author Antoine Aubé <aube.antoine@gmail.com>
 */
public class Hotel {

  private String name;
  private String city;
  private int roomCost;

  public Hotel(String name, String city, int roomCost) {
    this.name = name;
    this.city = city;
    this.roomCost = roomCost;
  }

  public String getName() {
    return name;
  }

  public String getCity() {
    return city;
  }

  public int getRoomCost() {
    return roomCost;
  }
}
