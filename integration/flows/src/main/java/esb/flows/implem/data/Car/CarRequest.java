package esb.flows.implem.data.Car;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class CarRequest implements Serializable {

    @JsonProperty private String place;
    @JsonProperty private String dateStart;
    @JsonProperty private String dateEnd;

    public String getPlace() { return place; }
    public void setPlace(String place) { this.place = place; }

    public String getDateStart() { return dateStart; }
    public void setDateStart(String dateStart) { this.dateStart = dateStart; }

    public String getDateEnd() { return dateEnd; }
    public void setDateEnd(String dateEnd) { this.dateEnd = dateEnd; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarRequest request = (CarRequest) o;

        if (dateStart != null ? !dateStart.equals(request.dateStart) : request.dateStart != null) return false;
        if (dateEnd != null ? !dateEnd.equals(request.dateEnd) : request.dateEnd != null) return false;
        return place != null ? place.equals(request.place) : request.place == null;
    }
}