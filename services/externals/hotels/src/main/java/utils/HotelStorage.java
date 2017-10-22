package utils;

import business.Hotel;
import business.HotelRequest;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Antoine Aubé <aube.antoine@gmail.com>
 */
public class HotelStorage {

  // Reference date for the comparison: 14. march 1995 at 6 PM.
  private static final Date COMPARATOR_DATE = new Date(795200400);

  // TODO Bind to a true database.
  private static List<Hotel> contents = new ArrayList<>();

  public static void create(Hotel hotel) {
    contents.add(hotel);
  }

  public static List<Hotel> findAllBy(HotelRequest request) {
    List<Hotel> relevantHotels = contents.stream()
            .filter(hotel -> {
              // Removes the hotels by location.
              return request.getCity()
                      // Check the city if present.
                      .map(city -> city.equals(hotel.getCity()))
                      // Accept all if not present.
                      .orElse(true);
            }).filter(hotel -> {
              // Removes the hotels by availability.
              // TODO Implement the hotel's availability.
              return request.getAvailability()
                      .map(availability -> availability.after(COMPARATOR_DATE))
                      .orElse(true);

            }).collect(Collectors.toList());

    // Ordering by price if requested.
    request.orderByAscendingPrice().map(orderByAscendingPrice -> {
      if (orderByAscendingPrice) {
        relevantHotels.sort(Comparator.comparingInt(Hotel::getRoomCost));
      } else {
        relevantHotels.sort(
                Comparator.comparingInt(Hotel::getRoomCost).reversed());
      }

      // Useless, yet necessary returned value.
      return 0;
    });
    return relevantHotels;
  }

  static {
    ClassLoader classLoader = HotelStorage.class.getClassLoader();
    File hotelsFile = new File(classLoader.getResource("hotels-data.csv").getFile());

    try {
      CSVParser parser = CSVParser.parse(hotelsFile, Charset.defaultCharset(), CSVFormat.DEFAULT.withHeader());

      parser.forEach(record -> {
        // Line into hotel.
        Hotel hotel = new Hotel(
                record.get("name"),
                record.get("city"),
                Integer.parseInt(record.get("roomCost")));

        // Register in the map.
        create(hotel);
      });

      parser.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
