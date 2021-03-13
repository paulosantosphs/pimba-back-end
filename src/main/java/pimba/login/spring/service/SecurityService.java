package pimba.login.spring.service;

import pimba.login.model.user.User;

import java.util.Optional;

public interface SecurityService {

    Optional<User> getCurrentUser();


}
