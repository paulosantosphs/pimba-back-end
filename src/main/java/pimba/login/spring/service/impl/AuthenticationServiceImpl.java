package pimba.login.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import pimba.login.model.passport.PassportType;
import pimba.login.model.passport.facebook.FacebookPassportAuthenticator;
import pimba.login.model.user.User;
import pimba.login.service.FacebookService;
import pimba.login.service.UserService;
import pimba.login.spring.config.TokenUtils;
import pimba.login.spring.service.AuthenticationService;

@Component
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private FacebookService facebookService;

    @Autowired
    private FacebookPassportAuthenticator facebookPassportAuthenticator;

    public String loginCommon(String username, PassportType type, String authString) {

        userService.authenticateCommon(username, type, authString).orElseThrow(() -> new BadCredentialsException("Usuário e/ou senha inválidos"));

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

        // Perform the authentication
        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities()));

        // Reload password post-authentication so we can generate token
        return this.tokenUtils.generateToken(userDetails);
    }

    public String refresh(String token) {
        if (this.tokenUtils.canTokenBeRefreshed(token)) {
            return this.tokenUtils.refreshToken(token);
        } else {
            return null;
        }
    }

    @Override
    public User registerCommon(String username, PassportType type, String password, String name) {
        if (PassportType.PASSWORD.equals(type)) {
            return userService.registerCommon(username, password, name);
        } else {
            return userService.authenticateCommon(username, type, password).orElseThrow(() -> new BadCredentialsException("Algum dado inválido foi informado"));
        }
    }

 /*   @Override
    public User registerCustomer(String username, PassportType type, String password, Customer customer) {
        if (PassportType.PASSWORD.equals(type)) {
            return userService.registerCustomer(username, password, customer);
        } else {
            return userService.authenticateUser(username, type, password).orElseThrow(() -> new BadCredentialsException("Algum dado inválido foi informado"));
        }
    }
*/

    public String facebookCommon(String accessToken) {
        User user = facebookPassportAuthenticator.authenticateCommon("", accessToken).get();
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(user.getEmail());
        return this.tokenUtils.generateToken(userDetails);
    }

}
