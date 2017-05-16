package pimba.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by paulo on 26/04/17.
 */
@Component
@ConfigurationProperties(prefix = "mapbox")
public class MapBoxProperties {

    private String token;

    public MapBoxProperties() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
