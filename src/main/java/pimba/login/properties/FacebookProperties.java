package pimba.login.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by idealize on 07/03/17.
 */
@Component
@ConfigurationProperties(prefix = "facebook")
public class FacebookProperties {

    private String commonLogin;

    private String customerLogin;

    public String getCommonLogin() {
        return commonLogin;
    }

    public void setCommonLogin(String commonLogin) {
        this.commonLogin = commonLogin;
    }

    public String getCustomerLogin() {
        return customerLogin;
    }

    public void setCustomerLogin(String customerLogin) {
        this.customerLogin = customerLogin;
    }
}
