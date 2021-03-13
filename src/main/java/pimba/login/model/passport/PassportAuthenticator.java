package pimba.login.model.passport;

import pimba.login.model.user.User;

import java.util.Optional;

public abstract class PassportAuthenticator {

    abstract public Optional<User> authenticateCommon(String email, String authCode);

}
