package pimba.login.model.passport.password;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pimba.login.model.passport.Passport;
import pimba.login.model.user.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(PasswordPassport.DESCRIMINATOR)
public class PasswordPassport extends Passport {
    public static final String DESCRIMINATOR = "PasswordPassport";

    @JsonIgnore
    private String encryptedPassword;

    // constructor
    public PasswordPassport(User user) {
        super(user);
    }

    protected PasswordPassport() {
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setPassword(String password) {
        this.encryptedPassword = new BCryptPasswordEncoder().encode(password);
    }

}
