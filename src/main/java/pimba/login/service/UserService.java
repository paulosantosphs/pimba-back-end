package pimba.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;
import pimba.domain.common.Common;
import pimba.domain.common.CommonRepository;
import pimba.domain.customer.Customer;
import pimba.login.gravatar.MD5Util;
import pimba.login.model.passport.Passport;
import pimba.login.model.passport.PassportAuthenticator;
import pimba.login.model.passport.PassportType;
import pimba.login.model.passport.password.PasswordPassport;
import pimba.login.model.user.User;
import pimba.login.repository.PassportRepository;
import pimba.login.repository.UserRepository;

import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private PassportRepository passportRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    @Autowired
    private CommonRepository commonRepository;

    /**
     * Dado um usuário e tipo de passaporte método retorna o passaporte
     *
     * @param user
     * @param type
     * @return passaporte do tipo requisitado
     */
    public Optional<Passport> getPassport(User user, PassportType type) {
        return passportRepo.findByUserIdAndType(user.getId(), type.getType());
    }

    /**
     * Dado um email, função retorna usuário que possui o email dado
     *
     * @param email
     * @return usuario com dado email se existir e null caso contrario
     */
    public Optional<User> findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    /**
     * Dado um usuario o tipo do passaporte e senha, método retorna se ele é
     * valido e se a senha está correta
     *
     * @param user
     * @param type
     * @param senha
     * @return retorna usuario autenticado
     */
    public Optional<User> authenticateCommon(String email, PassportType type, String authString) {

        if (type == null) {
            throw new RuntimeException("Tipo de autenticação inválido.");
        }

        try {
            // injeta o autenticador manualmente
            PassportAuthenticator authenticator = type.getAuthenticatorType().newInstance();
            beanFactory.autowireBean(authenticator);
            return authenticator.authenticateCommon(email, authString);
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

    /**
     * Registra usuario no banco dado email e senha ou retorna usuario existente
     * com o mesmo email mas não altera senha. Caso usuario ja exista mas nao
     * tenha passaporte de senha, adiciona passaporte de senha e retorna usuario
     *
     * @param email
     * @param password
     * @return retorna usuario registrado
     */
    public User registerCommon(String email, String password, String name) {

        if (!userRepo.findByEmail(email).isPresent()) {
            Common common = new Common(name, createPhotoUrl(email), email);
            User user = new User(email, common);
            PasswordPassport wordPass = new PasswordPassport(user);
            wordPass.setPassword(password);
            commonRepository.save(common);
            userRepo.save(user);
            passportRepo.save(wordPass);

            return user;
        }

        User user = userRepo.findByEmail(email).get();
        if (!passportRepo.findByUserIdAndType(user.getId(),
                PassportType.PASSWORD.getType()).isPresent()) {
            PasswordPassport wordPass = new PasswordPassport(user);
            wordPass.setPassword(password);
            passportRepo.save(wordPass);
        }

        return user;
    }

    public User registerCustomer(String email, String password, Customer customer) {

        if (!userRepo.findByEmail(email).isPresent()) {

            User user = new User(email, customer);
            PasswordPassport wordPass = new PasswordPassport(user);
            wordPass.setPassword(password);

            userRepo.save(user);
            passportRepo.save(wordPass);

            return user;
        }

        User user = userRepo.findByEmail(email).get();
        if (!passportRepo.findByUserIdAndType(user.getId(),
                PassportType.PASSWORD.getType()).isPresent()) {
            PasswordPassport wordPass = new PasswordPassport(user);
            wordPass.setPassword(password);
            passportRepo.save(wordPass);
        }

        return user;
    }

    private String createPhotoUrl(String email) {
        email = email.toLowerCase();
        String hash = MD5Util.md5Hex(email);
        String url = "https://www.gravatar.com/avatar/" + hash + "?s=256";
        return url;
    }

}
