package pimba.login.model.passport;

import pimba.login.model.passport.facebook.FacebookPassport;
import pimba.login.model.passport.facebook.FacebookPassportAuthenticator;
import pimba.login.model.passport.password.PasswordPassport;
import pimba.login.model.passport.password.PasswordPassportAuthenticator;

public enum PassportType {
    PASSWORD(PasswordPassport.DESCRIMINATOR, PasswordPassport.class, PasswordPassportAuthenticator.class),
    FACEBOOK(FacebookPassport.DESCRIMINATOR, FacebookPassport.class, FacebookPassportAuthenticator.class);

    private String descriminator;
    private Class<? extends Passport> type;
    private Class<? extends PassportAuthenticator> authenticatorType;

    private PassportType(String descriminator, Class<? extends Passport> type,
                         Class<? extends PassportAuthenticator> authenticatorType) {
        this.descriminator = descriminator;
        this.type = type;
        this.authenticatorType = authenticatorType;
    }

    public String getDescriminator() {
        return descriminator;
    }

    public Class<? extends Passport> getType() {
        return type;
    }

    public Class<? extends PassportAuthenticator> getAuthenticatorType() {
        return authenticatorType;
    }

}
