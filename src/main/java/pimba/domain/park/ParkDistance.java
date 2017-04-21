package pimba.domain.park;

/**
 * Created by paulo on 20/04/17.
 */
public class ParkDistance {
    private Park park;
    private Integer distance;
    private Integer time;

    public ParkDistance(Park park) {
        this.park = park;
    }

    public ParkDistance() {
    }

    public ParkDistance(Park park, Integer distance, Integer time) {
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

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
