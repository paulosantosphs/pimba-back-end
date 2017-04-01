package pimba.domain.customer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import pimba.domain.park.Park;
import pimba.entities.EntityWithTimestamps;
import pimba.login.model.user.User;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by paulo on 19/03/17.
 */
@Entity
public class Customer extends EntityWithTimestamps {
    private static final long serialVersionUID = 1006491999502867927L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne(mappedBy = "customer")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Park> parks;

    public Customer(String name, Set<Park> parks) {
        super();
        this.name = name;
        this.parks = parks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Set<Park> getParks() {
        return parks;
    }

    public void setParks(Set<Park> parks) {
        this.parks = parks;
    }
}
