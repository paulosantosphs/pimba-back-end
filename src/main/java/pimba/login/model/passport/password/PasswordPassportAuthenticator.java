package pimba.login.model.passport.password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pimba.login.model.passport.PassportAuthenticator;
import pimba.login.model.user.User;
import pimba.login.repository.PassportRepository;
import pimba.login.repository.UserRepository;

import java.util.Optional;

public class PasswordPassportAuthenticator extends PassportAuthenticator {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PassportRepository passportRepo;

    @Override
    public Optional<User> authenticateCommon(String email, String password) {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Usuário não registrado"));
        PasswordPassport passport = (PasswordPassport) passportRepo.findByUserIdAndType(user.getId(), PasswordPassport.class).get();
        BCryptPasswordEncoder encrypted = new BCryptPasswordEncoder();
        return Optional.ofNullable(encrypted.matches(password, passport.getEncryptedPassword()) ? user : null);
    }

}
