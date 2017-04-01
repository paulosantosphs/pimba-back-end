package pimba.login.spring.model.json;

import pimba.login.model.passport.PassportType;

import javax.validation.constraints.NotNull;

/**
 * Created by paulo on 01/04/17.
 */
public class CommonRegistrationRequest {
    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private PassportType type;

    public CommonRegistrationRequest() {
        super();
    }

    public CommonRegistrationRequest(String name, String email, String password, PassportType type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PassportType getType() {
        return type;
    }

    public void setType(PassportType type) {
        this.type = type;
    }
}
