package pimba.login.spring.service;

import pimba.login.model.passport.PassportType;
import pimba.login.model.user.User;

public interface AuthenticationService {

    public String loginCommon(String username, PassportType type, String password);

    public String refresh(String token);

    public User registerCommon(String username, PassportType type, String password, String name);

    //  public User registerCustomer(String username, PassportType type, String password, Customer customer);

    public String facebookCommon(String authCode);
}
