package pimba.login.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pimba.login.model.user.User;

/**
 * Created by paulo on 28/04/17.
 */
@Service
public class CurrentUserVerication {
    @Autowired
    SecurityService securityService;

    public Boolean isCurrentUserCommon() {
        User user = securityService.getCurrentUser().get();
        return (user.getCommon() != null);
    }

    public Boolean isCurrentUserCustomer() {
        User user = securityService.getCurrentUser().get();
        return (user.getCustomer() != null);
    }
}
