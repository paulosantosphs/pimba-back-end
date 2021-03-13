package pimba.domain.common;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import pimba.domain.common.historic.Historic;
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
    private static final long serialVersionUID = -2453528703899001011L;

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String phone;

    private String photo;

    private String city;

    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String credit;

    @OneToOne(mappedBy = "common")
    @JsonBackReference
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "common", cascade = CascadeType.ALL)
    private Set<Vehicle> vehicles;

    @OneToMany(mappedBy = "common")
    @JsonBackReference
    private Set<Comment> comments;

    @OneToMany(mappedBy = "common")
    @JsonIgnore
    private Set<Evaluation> evaluations;

    @OneToMany(mappedBy = "common")
    @JsonIgnore
    private Set<Historic> historic;

    public Common() {
        super();
    }

    public Common(String name) {
        this.name = name;
    }

    public Common(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Common(String name, String photo, String email) {
        this.name = name;
        this.photo = photo;
        this.email = email;
    }

    public Common(String name, String phone, String photo, String city, String email, Gender gender) {
        this.name = name;
        this.phone = phone;
        this.photo = photo;
        this.city = city;
        this.email = email;
        this.gender = gender;
    }

    public Common(String name, String phone, String photo, String city, String email, Gender gender, String credit, User user, Set<Vehicle> vehicles, Set<Comment> comments, Set<Evaluation> evaluations, Set<Historic> historic) {
        this.name = name;
        this.phone = phone;
        this.photo = photo;
        this.city = city;
        this.email = email;
        this.gender = gender;
        this.credit = credit;
        this.user = user;
        this.vehicles = vehicles;
        this.comments = comments;
        this.evaluations = evaluations;
        this.historic = historic;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
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

    public Set<Historic> getHistoric() {
        return historic;
    }

    public void setHistoric(Set<Historic> historic) {
        this.historic = historic;
    }
}
