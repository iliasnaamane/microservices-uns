package esb.flows.implem.data;

import java.io.Serializable;

public class Request implements Serializable {

    private String place;
    private String dateStart;
    private String dateEnd;

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

        Request request = (Request) o;

        if (dateStart != null ? !dateStart.equals(request.dateStart) : request.dateStart != null) return false;
        if (dateEnd != null ? !dateEnd.equals(request.dateEnd) : request.dateEnd != null) return false;
        return place != null ? place.equals(request.place) : request.place == null;
    }
}