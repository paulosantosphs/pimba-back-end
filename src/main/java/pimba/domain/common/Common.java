package pimba.domain.common;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import pimba.domain.park.comment.Comment;
import pimba.domain.park.evaluation.Evaluation;
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

    private static final long serialVersionUID = 5426467790129670258L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne(mappedBy = "common")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "common", cascade = CascadeType.ALL)
    private Set<Vehicle> vehicles;

    @OneToMany(mappedBy = "common")
    @JsonBackReference
    private Set<Comment> comments;

    @OneToMany(mappedBy = "common")
    @JsonIgnore
    private Set<Evaluation> evaluations;

    public Common() {
        super();
    }

    public Common(String name) {
        this.name = name;
    }

    public Common(String name, User user, Set<Vehicle> vehicles, Set<Comment> comments, Set<Evaluation> evaluations) {
        this.name = name;
        this.user = user;
        this.vehicles = vehicles;
        this.comments = comments;
        this.evaluations = evaluations;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(Set<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }
}
