package pimba.login.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pimba.login.model.user.User;
import pimba.login.repository.UserRepository;
import pimba.login.spring.service.SecurityService;

import java.util.Optional;

@Component
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private UserRepository userRepo;

    public Optional<User> getCurrentUser() {
        Authentication details = SecurityContextHolder.getContext().getAuthentication();

        if (details == null) {
            return Optional.ofNullable(null);
        }

        return userRepo.findByEmail(details.getName());
    }


}
