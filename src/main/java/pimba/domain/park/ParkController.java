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
    public Park getParkbyName(@RequestParam String name) {
        return parkService.getParkByName(name);
    }
}
