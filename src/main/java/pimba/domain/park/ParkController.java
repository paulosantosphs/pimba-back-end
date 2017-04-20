package pimba.domain.park;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ParkResponse getParkListByLocation(@RequestParam String location, Double userLatitude, Double userLongitude) {
        return parkService.getParksByLocation(location, userLatitude, userLongitude, 10);
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "${park.coordinates}", method = RequestMethod.GET)
    public ParkResponse getParkListByCoordinates(@RequestParam Double pointLatitude, Double pointLongitude, Double userLatitude, Double userLongitude) {
        return parkService.getParksByCoordinates(pointLatitude, pointLongitude, userLatitude, userLongitude, 10);
    }

}
