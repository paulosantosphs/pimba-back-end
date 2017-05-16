package pimba.domain.common.historic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pimba.login.spring.service.CurrentUserVerication;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by paulo on 11/05/17.
 */

@RestController
@RequestMapping("${historic.base}")
public class HistoricController {

    @Autowired
    private CurrentUserVerication currentUserVerication;

    @Autowired
    private HistoricService service;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Historic>> getHistoric() {
        if (currentUserVerication.isCurrentUserCommon()) {
            return ResponseEntity.ok(service.getHistoric());
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "${historic.register}", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Historic> registerHistoric(@RequestBody @Valid HistoricRegisterRequest request) {
        if (currentUserVerication.isCurrentUserCommon()) {
            Historic historic = service.register(request.getPayment(), request.getParkId(), request.getBotton());
            return ResponseEntity.ok(historic);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

    }

}
