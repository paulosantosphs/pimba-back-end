package pimba.login.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pimba.login.spring.model.json.AuthenticationRequest;
import pimba.login.spring.model.json.AuthenticationResponse;
import pimba.login.spring.model.json.CommonRegistrationRequest;
import pimba.login.spring.model.json.FacebookAuthenticationRequest;
import pimba.login.spring.service.AuthenticationService;
import pimba.login.spring.service.SecurityService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("${login.route.authentication.base}")
public class AuthenticationController {

    @Value("${login.token.header}")
    private String tokenHeader;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "${login.route.authentication.signIn}", method = RequestMethod.POST)
    public ResponseEntity<?> signIn(@RequestBody(required = true) @Validated AuthenticationRequest authenticationRequest) {
        String token = this.authenticationService.loginCommon(
                authenticationRequest.getEmail(),
                authenticationRequest.getType(),
                authenticationRequest.getPassword());

        // Return the token
        return ResponseEntity.ok().header(tokenHeader, token).body(new AuthenticationResponse(token));
    }

    @RequestMapping(value = "${login.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> authenticationRequest(HttpServletRequest request) {
        String refreshedToken = this.authenticationService.refresh(request.getHeader(this.tokenHeader));
        if (refreshedToken != null) {
            return ResponseEntity.ok(new AuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(value = "${login.route.authentication.commonRegister}", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody(required = true) @Validated CommonRegistrationRequest commonRegistrationRequest) {
        return ResponseEntity.ok(this.authenticationService.registerCommon(
                commonRegistrationRequest.getEmail(),
                commonRegistrationRequest.getType(),
                commonRegistrationRequest.getPassword(),
                commonRegistrationRequest.getName()));
    }

    @RequestMapping(value = "${login.route.authentication.currentUser}", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> me() {
        return ResponseEntity.ok(securityService.getCurrentUser().get());
    }

    @RequestMapping(value = "${login.route.authentication.facebook.commonLogin}", method = RequestMethod.POST)
    public ResponseEntity<?> facebookLogin(@RequestBody(required = true) @Validated FacebookAuthenticationRequest facebookAuthenticationRequest) {
        String token = this.authenticationService.facebookCommon(facebookAuthenticationRequest.getAccessToken());
        // Return the token
        return ResponseEntity.ok().header(tokenHeader, token).body(new AuthenticationResponse(token));
    }

}
