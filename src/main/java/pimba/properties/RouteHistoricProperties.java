package pimba.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by paulo on 11/05/17.
 */
@Component
@ConfigurationProperties(prefix = "historic")
public class RouteHistoricProperties {

    private String base;

    private String register;

    public RouteHistoricProperties() {
    }

    public RouteHistoricProperties(String base, String register) {
        this.base = base;
        this.register = register;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }
}
