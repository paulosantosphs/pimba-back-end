package pimba.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by paulo on 13/05/17.
 */
@Component
@ConfigurationProperties(prefix = "comment")
public class RouteCommentProperties {

    private String base;

    private String save;

    public RouteCommentProperties() {
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getSave() {
        return save;
    }

    public void setSave(String save) {
        this.save = save;
    }
}
