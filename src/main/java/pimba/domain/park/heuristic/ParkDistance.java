package pimba.domain.park.heuristic;

/**
 * Created by paulo on 17/05/17.
 */
public class ParkDistance {

    private Integer parkId;

    private Integer distance;

    private Integer time;

    public ParkDistance() {
    }

    public ParkDistance(Integer parkId, Integer distance, Integer time) {
        this.parkId = parkId;
        this.distance = distance;
        this.time = time;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
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
