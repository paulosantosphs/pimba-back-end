package pimba.domain.park;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Park> getParkListByLocation(@RequestParam String location) {
        return parkService.getParkList(location, 10);
    }
}
