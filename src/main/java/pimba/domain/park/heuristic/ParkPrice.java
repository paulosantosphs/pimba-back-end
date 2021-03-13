package pimba.domain.park.heuristic;

/**
 * Created by paulo on 17/05/17.
 */
public class ParkPrice {

    private Integer parkId;

    private  double price;

    public ParkPrice() {
    }

    public ParkPrice(Integer parkId) {
        this.parkId = parkId;
    }

    public ParkPrice(Integer parkId, double price) {
        this.parkId = parkId;
        this.price = price;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
