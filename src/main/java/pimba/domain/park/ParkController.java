package pimba.domain.park;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pimba.domain.park.rate.RatePeriod;

/**
 * Created by paulo on 02/04/17.
 */

@RestController
@RequestMapping("${park.base}")
public class ParkController {

    @Autowired
    private ParkService parkService;

    @CrossOrigin(origins = "*")
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Park getParkByName(@RequestParam String name) {
        return parkService.getParkByName(name);
    }


    @CrossOrigin(origins = "*")
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "${park.list}", method = RequestMethod.GET)
    public ResponseEntity<ParkResponse> getParkListByLocation(@RequestParam String location) {
        if (location.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(parkService.getParksByLocation(location, 10));
        }

    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "${park.list}/${park.coordinates}", method = RequestMethod.GET)
    public ResponseEntity<ParkResponse> getParkListByCoordinates(@RequestParam Double pointLatitude, Double pointLongitude) {
        if (pointLatitude == null || pointLongitude == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(parkService.getParksByCoordinates(pointLatitude, pointLongitude, 10));
        }
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "${park.best}", method = RequestMethod.GET)
    public ResponseEntity<Park> getBestParktByLocation(@RequestParam String location, RatePeriod period) {
        if (location.isEmpty() || period == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(parkService.getBestParkByLocation(location, 10, period));
        }

    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "${park.best}/${park.coordinates}", method = RequestMethod.GET)
    public ResponseEntity<Park> getBestParkByCoordinates(@RequestParam Double pointLatitude, Double pointLongitude, RatePeriod period) {
        if (pointLatitude == null || pointLongitude == null || period == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(parkService.getBestParkByCoordinates(pointLatitude, pointLongitude, 10, period));
        }
    }

}
