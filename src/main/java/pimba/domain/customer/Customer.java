package pimba.domain.customer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import pimba.domain.customer.plan.Plan;
import pimba.domain.customer.promotion.Promotion;
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
    private static final long serialVersionUID = -8985984704297484175L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String phone;

    private String email;

    @OneToOne(mappedBy = "customer")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Park> parks;

    @OneToMany(mappedBy = "customer")
    private Set<Plan> plans;

    @OneToMany(mappedBy = "customer")
    private Set<Promotion> promotions;

    public Customer() {
        super();
    }

    public Customer(String name, String phone, String email, User user, Set<Park> parks, Set<Plan> plans, Set<Promotion> promotions) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.user = user;
        this.parks = parks;
        this.plans = plans;
        this.promotions = promotions;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Park> getParks() {
        return parks;
    }

    public void setParks(Set<Park> parks) {
        this.parks = parks;
    }

    public Set<Plan> getPlans() {
        return plans;
    }

    public void setPlans(Set<Plan> plans) {
        this.plans = plans;
    }

    public Set<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(Set<Promotion> promotions) {
        this.promotions = promotions;
    }
}
