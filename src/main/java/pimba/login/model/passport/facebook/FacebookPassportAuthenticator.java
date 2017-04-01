package pimba.login.model.passport.facebook;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pimba.domain.common.Common;
import pimba.domain.common.CommonRepository;
import pimba.login.model.passport.PassportAuthenticator;
import pimba.login.model.user.User;
import pimba.login.repository.PassportRepository;
import pimba.login.repository.UserRepository;
import pimba.login.service.FacebookService;

import java.util.Optional;

@Component
public class FacebookPassportAuthenticator extends PassportAuthenticator {

    @Autowired
    private FacebookService facebook;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PassportRepository passportRepo;
    @Autowired
    private CommonRepository commonRepository;

    @Override
    public Optional<User> authenticateCommon(String email, String accessToken) {
        try {
            JSONObject obj = facebook.getUserFields(accessToken, "email", "name");

            String realEmail = obj.getString("email");
            String name = obj.getString("name");

            User user = userRepo.findByEmail(realEmail).orElseGet(() -> {
                Common newCommon = new Common(name);
                User newUser = new User(realEmail, newCommon);
                commonRepository.save(newCommon);
                return userRepo.save(newUser);
            });
            FacebookPassport passport = (FacebookPassport) passportRepo
                    .findByUserIdAndType(user.getId(), FacebookPassport.class).orElseGet(() -> {
                        FacebookPassport newPassport = new FacebookPassport(user, accessToken);
                        return passportRepo.save(newPassport);
                    });
            passport.setAccessToken(accessToken);
            passportRepo.save(passport);
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.ofNullable(null);
        }

    }
}
