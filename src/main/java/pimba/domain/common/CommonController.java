package pimba.domain.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pimba.login.spring.service.CurrentUserVerication;
import pimba.login.spring.service.SecurityService;

/**
 * Created by paulo on 26/04/17.
 */
@RestController
@RequestMapping("${common.base}")
public class CommonController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private CurrentUserVerication currentUserVerication;

    @Autowired
    private CommonService commonService;


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Common> getCurrentCommonUser() {
        if (currentUserVerication.isCurrentUserCommon()) {
            return ResponseEntity.ok(securityService.getCurrentUser().get().getCommon());
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/${common.edit.base}/${common.edit.name}", method = RequestMethod.PUT)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Common> editName(@RequestParam String name) {
        if (name.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        } else if (currentUserVerication.isCurrentUserCommon()) {
            return ResponseEntity.ok(commonService.editName(name));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/${common.edit.base}/${common.edit.phone}", method = RequestMethod.PUT)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Common> editPhone(@RequestParam String phone) {
        if (phone.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        } else if (currentUserVerication.isCurrentUserCommon()) {
            return ResponseEntity.ok(commonService.editPhone(phone));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/${common.edit.base}/${common.edit.gender}", method = RequestMethod.PUT)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Common> editGender(@RequestParam Gender gender) {
        if (gender == null) {
            return ResponseEntity.badRequest().body(null);
        } else if (currentUserVerication.isCurrentUserCommon()) {
            return ResponseEntity.ok(commonService.editGender(gender));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/${common.edit.base}/${common.edit.photo}", method = RequestMethod.PUT)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Common> editPhoto(@RequestParam String photo) {
        if (photo.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        } else if (currentUserVerication.isCurrentUserCommon()) {
            return ResponseEntity.ok(commonService.editPhoto(photo));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/${common.edit.base}/${common.edit.city}", method = RequestMethod.PUT)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Common> editCity(@RequestParam String city) {
        if (city.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        } else if (currentUserVerication.isCurrentUserCommon()) {
            return ResponseEntity.ok(commonService.editCity(city));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

}
