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

    public List<Park> getParkList(String location, double radius) {
        String query = query(location);
        JSONArray coords = getCoordinates(query);
        Double latitude = coords.getDouble(1);
        Double longitude = coords.getDouble(0);
        return parkRepository.getListParkByLocation(latitude, longitude, latitudeRadius(radius), longitudeRadius(radius));
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
            throw new LocationException(e.getMessage());
        }
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
    private double latitudeRadius(Double radius) {
        return 0.009044 * radius;

    }

    private double longitudeRadius(Double radius) {
        return 0.0089831 * radius;
    }

}
