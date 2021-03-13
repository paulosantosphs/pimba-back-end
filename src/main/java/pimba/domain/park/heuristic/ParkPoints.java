package pimba.domain.park.heuristic;

/**
 * Created by paulo on 17/05/17.
 */
public class ParkPoints {

    private Integer parkId;

    private float distancePoint;

    private float pricePoint;

    private int pimbaPoint;

    private float points;

    public ParkPoints() {
    }

    public ParkPoints(Integer parkId, float distancePoint, float pricePoint, int pimbaPoint, float points) {
        this.parkId = parkId;
        this.distancePoint = distancePoint;
        this.pricePoint = pricePoint;
        this.pimbaPoint = pimbaPoint;
        this.points = points;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public float getDistancePoint() {
        return distancePoint;
    }

    public void setDistancePoint(float distancePoint) {
        this.distancePoint = distancePoint;
    }

    public float getPricePoint() {
        return pricePoint;
    }

    public void setPricePoint(float pricePoint) {
        this.pricePoint = pricePoint;
    }

    public int getPimbaPoint() {
        return pimbaPoint;
    }

    public void setPimbaPoint(int pimbaPoint) {
        this.pimbaPoint = pimbaPoint;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }
}
