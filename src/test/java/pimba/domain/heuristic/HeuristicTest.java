package pimba.domain.heuristic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pimba.domain.address.Address;
import pimba.domain.customer.Customer;
import pimba.domain.park.Park;
import pimba.domain.park.ParkRepository;
import pimba.domain.park.heuristic.HeuristicService;
import pimba.domain.park.heuristic.ParkDistance;
import pimba.domain.park.heuristic.ParkPoints;
import pimba.domain.park.heuristic.ParkPrice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * Created by paulo on 18/05/17.
 */
public class HeuristicTest {
    @InjectMocks
    HeuristicService heuristic;

    @Mock
    ParkRepository parkRepository;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void putPointsTest() {
        List<ParkDistance> parksDistance = new ArrayList<>();
        ParkDistance parkDistance1 = new ParkDistance();
        ParkDistance parkDistance2 = new ParkDistance();
        ParkDistance parkDistance3 = new ParkDistance();

        parkDistance1.setParkId(1);
        parkDistance1.setDistance(100);

        parkDistance2.setParkId(2);
        parkDistance2.setDistance(300);

        parkDistance3.setParkId(3);
        parkDistance3.setDistance(200);

        parksDistance.add(parkDistance1);
        parksDistance.add(parkDistance2);
        parksDistance.add(parkDistance3);

        List<ParkPrice> parksPrice = new ArrayList<>();
        ParkPrice parkPrice1 = new ParkPrice();
        ParkPrice parkPrice2 = new ParkPrice();
        ParkPrice parkPrice3 = new ParkPrice();

        parkPrice1.setParkId(1);
        parkPrice1.setPrice(6);

        parkPrice2.setParkId(2);
        parkPrice2.setPrice(7);

        parkPrice3.setParkId(3);
        parkPrice3.setPrice(7.5);

        parksPrice.add(parkPrice1);
        parksPrice.add(parkPrice2);
        parksPrice.add(parkPrice3);


        Address address = new Address(-20.387719, -43.506921);
        Customer customer = new Customer("Bpark", "dhausidh");
        Park park1 = new Park(address, "Park", "park");
        Park park2 = new Park(address, "Park", "park");
        Park park3 = new Park(address, "Park", customer);
        when(parkRepository.findById(1)).thenReturn(Optional.of(park1));
        when(parkRepository.findById(2)).thenReturn(Optional.of(park2));
        when(parkRepository.findById(3)).thenReturn(Optional.of(park3));

        Collections.sort(parksDistance, (o2, o1) -> Double.compare(o1.getDistance(), o2.getDistance()));
        Collections.sort(parksPrice, (o2, o1) -> Double.compare(o1.getPrice(), o2.getPrice()));

        List<ParkPoints> parksPoints = heuristic.putPoints(parksDistance, parksPrice);
        int id1 = parksPoints.get(0).getParkId();
        int id2 = parksPoints.get(1).getParkId();
        int id3 = parksPoints.get(2).getParkId();

        Assert.assertEquals(1, id1);
        Assert.assertEquals(3, id2);
        Assert.assertEquals(2, id3);

    }

    @Test
    public void calculatePointsTest() {
        int distancePoint = 10;
        int pricePoint = 8;
        int pimbaPoint = 10;
        int distanceWeight = 1;
        int priceWeight = 1;
        float pimbaWeight = (float) 0.5;

        float points = heuristic.calculatePoints(distancePoint, pricePoint, pimbaPoint, distanceWeight, priceWeight, pimbaWeight);
        Assert.assertEquals(7.6666665, points, 0.01);
    }
}
