package pimba.domain.park.heuristic;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pimba.domain.park.Park;
import pimba.domain.park.ParkRepository;
import pimba.domain.park.ParkService;
import pimba.domain.park.rate.Rate;
import pimba.domain.park.rate.RatePeriod;
import pimba.domain.park.rate.RateRepository;

import java.util.*;

/**
 * Created by paulo on 17/05/17.
 */
@Service
public class HeuristicService {
    int DistanceWeight = 1;
    int PriceWeight = 1;
    float PimbaWeight = (float) 0.5;

    @Autowired
    ParkService parkService;

    @Autowired
    ParkRepository parkRepository;

    @Autowired
    RateRepository rateRepository;

    public Park bestPark(Double pointLatitude, Double pointLongitude, double radius, RatePeriod period) {
        List<Park> parks = parkRepository.getListParkByLocation(pointLatitude, pointLongitude, parkService.latitudeRadius(radius), parkService.longitudeRadius(radius));
        if (parks.isEmpty()) {
            return null;
        }
        String destinations = parkService.createDestinationsQuery(parks);
        JSONArray elements = parkService.getJsonDistance(destinations, pointLatitude, pointLongitude);
        List<ParkDistance> parksDistance = new ArrayList<>();
        List<ParkPrice> parksPrice = new ArrayList<>();
        int c = 0;
        for (Park park : parks) {
            JSONObject element = elements.getJSONObject(c);
            JSONObject dis = element.getJSONObject("distance");
            JSONObject duration = element.getJSONObject("duration");
            Integer distance = dis.getInt("value");
            Integer time = duration.getInt("value");
            ParkDistance parkDistance = new ParkDistance(park.getId(), distance, time);
            Rate rate = rateRepository.findByParkAndRatePeriod(park, period).orElseGet(() -> new Rate(new Park("Parksemrate")));
            if (rate.getPark().getName() != "Parksemrate") {
                ParkPrice parkPrice = new ParkPrice(park.getId(), rate.getPrice());
                parksPrice.add(parkPrice);
            }
            parksDistance.add(parkDistance);
            c++;
        }
        Collections.sort(parksDistance, (o2, o1) -> Double.compare(o1.getDistance(), o2.getDistance()));
        Collections.sort(parksPrice, (o2, o1) -> Double.compare(o1.getPrice(), o2.getPrice()));
        List<ParkPoints> parkPointses = putPoints(parksDistance, parksPrice);
        return parkRepository.findById(parkPointses.get(0).getParkId()).get();
    }

    public List<ParkPoints> putPoints(List<ParkDistance> parksDistance, List<ParkPrice> parksPrice) {
        float distancePoint;
        float pricePoint;
        Map<Integer, ParkPoints> map = new HashMap<>();
        for (ParkPrice price : parksPrice) {
            ParkPoints parkPoints = new ParkPoints();
            pricePoint = (float) ((float) (10) * ((float) parksPrice.get(parksPrice.size() - 1).getPrice() / price.getPrice()));
            parkPoints.setParkId(price.getParkId());
            parkPoints.setPricePoint(pricePoint);
            map.put(price.getParkId(), parkPoints);
        }
        for (ParkDistance distance : parksDistance) {
            distancePoint = (float) (10) * (((float) parksDistance.get(parksDistance.size() - 1).getDistance()) / ((float) distance.getDistance()));
            ParkPoints parkPoints;
            if (!map.containsKey(distance.getParkId())) {
                parkPoints = new ParkPoints();
                parkPoints.setDistancePoint(distancePoint);
                parkPoints.setParkId(distance.getParkId());
                map.put(distance.getParkId(), parkPoints);
            } else {
                parkPoints = map.get(distance.getParkId());
                parkPoints.setDistancePoint(distancePoint);
            }
            if (parkRepository.findById(distance.getParkId()).get().getCustomer() == null) {
                parkPoints.setPimbaPoint(0);
            } else {
                parkPoints.setPimbaPoint(10);
            }
            parkPoints.setPoints(calculatePoints(distancePoint, parkPoints.getPricePoint(), parkPoints.getPimbaPoint(), DistanceWeight, PriceWeight, PimbaWeight));
            map.replace(distance.getParkId(), parkPoints);
        }
        List<ParkPoints> parkPointses = new ArrayList<>(map.values());
        Collections.sort(parkPointses, (o2, o1) -> Double.compare(o1.getPoints(), o2.getPoints()));
        return parkPointses;
    }

    public float calculatePoints(float distancePoint, float pricePoint, int pimbaPoint, int distanceWeight, int priceWeight, float pimbaWeight) {
        float distance = distancePoint * distanceWeight;
        float price = pricePoint * priceWeight;
        float pimba = pimbaPoint * pimbaWeight;
        float points = (distance + price + pimba) / ((float) 3);
        return points;
    }


}
