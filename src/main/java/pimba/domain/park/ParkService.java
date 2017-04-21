package pimba.domain.park;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pimba.exceptions.InvalidParkException;
import pimba.exceptions.LocationException;

import java.io.IOException;
import java.net.URL;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulo on 02/04/17.
 */
@Service
public class ParkService {
    @Autowired
    private ParkRepository parkRepository;


    public Park getParkByName(String name) {
        Park park = parkRepository.findByName(name).orElseThrow(() -> new InvalidParkException("Parking not found"));
        return park;
    }

    public ParkResponse getParksByCoordinates(Double pointLatitude, Double pointLongitude, double radius) {
        if (pointLatitude == null || pointLongitude == null) {
            throw new LocationException("pointLatitude or pointLongitude are null");
        }
        return getParks(pointLatitude, pointLongitude, radius);
    }

    public ParkResponse getParksByLocation(String location, double radius) {
        if (location.isEmpty()) {
            throw new LocationException("Location is null");
        }
        String query = query(location);
        JSONArray coords = getCoordinates(query);
        Double latitude = coords.getDouble(1);
        Double longitude = coords.getDouble(0);
        return getParks(latitude, longitude, radius);
    }

    public ParkResponse getParks(Double pointLatitude, Double pointLongitude, double radius) {
        List<Park> parks = parkRepository.getListParkByLocation(pointLatitude, pointLongitude, latitudeRadius(radius), longitudeRadius(radius));
        if (parks.isEmpty()) {
            return new ParkResponse(new ArrayList<>(), pointLatitude, pointLongitude);
        }
        String destinations = createDestinationsQuery(parks);
        JSONArray elements = getJsonDistance(destinations, pointLatitude, pointLongitude);
        List<ParkDistance> parkDistances = new ArrayList<>();
        int c = 0;
        for (Park park : parks) {
            JSONObject element = elements.getJSONObject(c);
            JSONObject dis = element.getJSONObject("distance");
            JSONObject duration = element.getJSONObject("duration");
            Integer distance = dis.getInt("value");
            Integer time = duration.getInt("value");
            ParkDistance parkDistance = new ParkDistance(park, distance, time);
            parkDistances.add(parkDistance);
            c++;
        }
        ParkResponse parkResponse = new ParkResponse(parkDistances, pointLatitude, pointLongitude);
        return parkResponse;
    }

    public JSONArray getCoordinates(String query) {
        String url = "https://api.mapbox.com/geocoding/v5/mapbox.places/" + query + ".json?access_token=pk.eyJ1IjoicGF1bG9zYW50b3NwaHMiLCJhIjoiY2oxN3k5NHhzMDZvdzJ3bXo5Nzh5ZGZlNCJ9.SjLN3Nwnqq0VnByM3f6wKQ&country=br&proximity=&bbox=&types=address%2Cneighborhood%2Clocality%2Cplace&autocomplete=false&limit=1";
        try {
            URL request = new URL(url);
            JSONTokener tokener = new JSONTokener(request.openStream());
            JSONObject obj = new JSONObject(tokener);
            JSONArray features = obj.getJSONArray("features");
            JSONObject feature = features.getJSONObject(0);
            JSONObject geometry = feature.getJSONObject("geometry");
            return geometry.getJSONArray("coordinates");
        } catch (JSONException | IOException e) {
            throw new LocationException("Address is incomplete");
        }
    }


    public JSONArray getJsonDistance(String query, Double pointLatitude, Double pointLongitude) {
        String origins = pointLatitude.toString() + "," + pointLongitude.toString();
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&language=pt-BR&origins=" + origins + "&destinations=" + query;
        try {
            URL request = new URL(url);
            JSONTokener tokener = new JSONTokener(request.openStream());
            JSONObject obj = new JSONObject(tokener);
            JSONArray rows = obj.getJSONArray("rows");
            JSONObject row = rows.getJSONObject(0);
            JSONArray elements = row.getJSONArray("elements");
            return elements;
        } catch (JSONException | IOException e) {
            throw new LocationException(e.getMessage());
        }
    }

    public String createDestinationsQuery(List<Park> parks) {
        String query = "";
        for (Park park : parks) {
            query = query + park.getAddress().getLatitude() + "," + park.getAddress().getLongitude() + "|";
        }
        return query;
    }

    public String removeAccents(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public String query(String location) {
        location = removeAccents(location);
        location = location.replace("-", "");
        location = location.replace(":", "");
        location = location.replace(".", "");
        location = location.replace(",", "");
        location = location.replace("  ", " ");
        location = location.replace(" ", ",");
        return location;
    }


    // radius(km)
    public double latitudeRadius(Double radius) {
        return 0.009044 * radius;

    }

    public double longitudeRadius(Double radius) {
        return 0.0089831 * radius;
    }

}
