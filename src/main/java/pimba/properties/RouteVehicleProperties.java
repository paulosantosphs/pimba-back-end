package pimba.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by paulo on 28/04/17.
 */
@Component
@ConfigurationProperties(prefix = "vehicle")
public class RouteVehicleProperties {

    private String base;

    private String vehicles;

    private String register;

    private RouteEditVehicle edit;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getVehicles() {
        return vehicles;
    }

    public void setVehicles(String vehicles) {
        this.vehicles = vehicles;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public RouteEditVehicle getEdit() {
        return edit;
    }

    public void setEdit(RouteEditVehicle edit) {
        this.edit = edit;
    }
}
