package pimba.domain.park;

/**
 * Created by paulo on 20/04/17.
 */
public class ParkDistance {
    private Park park;
    private String distance;
    private String time;

    public ParkDistance(Park park) {
        this.park = park;
    }

    public ParkDistance() {
    }

    public ParkDistance(Park park, String distance, String time) {
        this.park = park;
        this.distance = distance;
        this.time = time;
    }

    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
