package pimba.login.spring.service;

import pimba.login.model.user.User;

import java.util.Optional;

public interface SecurityService {

    public Optional<User> getCurrentUser();

    public Boolean isCurrentUserCommon();

    public Boolean isCurrentUserCustomer();

}
