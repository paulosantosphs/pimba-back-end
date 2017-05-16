package pimba.domain.park.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pimba.login.spring.service.CurrentUserVerication;

import javax.validation.Valid;

/**
 * Created by paulo on 11/05/17.
 */

@RestController
@RequestMapping("${comment.base}")
public class CommentController {

    @Autowired
    private CurrentUserVerication currentUserVerication;

    @Autowired
    private CommentService service;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "${comment.save}", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Comment> save(@RequestBody @Valid CommentSaveRequest request) {
        if (currentUserVerication.isCurrentUserCommon()) {
            return ResponseEntity.ok(service.register(request.getParkId(), request.getComment()));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

    }
}
