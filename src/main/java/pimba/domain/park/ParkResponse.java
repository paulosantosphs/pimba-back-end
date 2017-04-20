package pimba.domain.park;

import java.util.List;

/**
 * Created by paulo on 19/04/17.
 */
public class ParkResponse {
    private List<ParkDistance> parkDistances;
    private Double pointLatitude;
    private Double pointLongitude;

    public ParkResponse() {
    }

    public ParkResponse(List<ParkDistance> parkDistances, Double pointLatitude, Double pointLongitude) {
        this.parkDistances = parkDistances;
        this.pointLatitude = pointLatitude;
        this.pointLongitude = pointLongitude;
    }

    public List<ParkDistance> getParkDistances() {
        return parkDistances;
    }

    public void setParkDistances(List<ParkDistance> parkDistances) {
        this.parkDistances = parkDistances;
    }

    public Double getPointLatitude() {
        return pointLatitude;
    }

    public void setPointLatitude(Double pointLatitude) {
        this.pointLatitude = pointLatitude;
    }

    public Double getPointLongitude() {
        return pointLongitude;
    }

    public void setPointLongitude(Double pointLongitude) {
        this.pointLongitude = pointLongitude;
    }
}
