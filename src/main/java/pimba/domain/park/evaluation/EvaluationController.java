package pimba.domain.park.evaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pimba.login.spring.service.CurrentUserVerication;

import javax.validation.Valid;

/**
 * Created by paulo on 13/05/17.
 */

@RestController
@RequestMapping("${evaluation.base}")
public class EvaluationController {

    @Autowired
    private CurrentUserVerication currentUserVerication;

    @Autowired
    private EvaluationService service;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "${evaluation.save}", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Evaluation> save(@RequestBody @Valid EvaluationSaveRequest request) {
        if (currentUserVerication.isCurrentUserCommon()) {
            return ResponseEntity.ok(service.save(request.getParkId(), request.getStars()));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

    }
}
