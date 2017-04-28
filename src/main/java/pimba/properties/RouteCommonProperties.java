package pimba.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by paulo on 26/04/17.
 */
@Component
@ConfigurationProperties(prefix = "common")
public class RouteCommonProperties {
    private String base;

    private RouteEditProperties edit;

    public RouteEditProperties getEdit() {
        return edit;
    }

    public void setEdit(RouteEditProperties edit) {
        this.edit = edit;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }
}
