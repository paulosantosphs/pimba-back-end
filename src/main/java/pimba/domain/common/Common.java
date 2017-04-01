package pimba.domain.common;

import com.fasterxml.jackson.annotation.JsonBackReference;
import pimba.domain.vehicle.Vehicle;
import pimba.entities.EntityWithTimestamps;
import pimba.login.model.user.User;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by paulo on 19/03/17.
 */
@Entity
public class Common extends EntityWithTimestamps {

    private static final long serialVersionUID = 8963938961622788536L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne(mappedBy = "common")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "common", cascade = CascadeType.ALL)
    private Set<Vehicle> vehicles;

    public Common() {
        super();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Common(String name) {
        this.name = name;
    }

    public Common(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
