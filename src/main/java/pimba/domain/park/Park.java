package pimba.domain.park;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import pimba.domain.address.Address;
import pimba.domain.common.historic.Historic;
import pimba.domain.customer.Customer;
import pimba.domain.park.comment.Comment;
import pimba.domain.park.evaluation.Evaluation;
import pimba.domain.park.rate.Rate;
import pimba.entities.EntityWithTimestamps;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by paulo on 19/03/17.
 */
@Entity
public class Park extends EntityWithTimestamps {
    private static final long serialVersionUID = 6702671705503578496L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn
    private Address address;

    private String name;

    private String description;

    private Integer spots;

    private String phone;

    private String email;

    private Double evaluation;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @OneToMany(mappedBy = "park")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "park")
    @JsonIgnore
    private Set<Evaluation> evaluations;

    @OneToMany(mappedBy = "park")
    private Set<Rate> rates;

    @OneToMany(mappedBy = "park")
    @JsonBackReference
    private Set<Historic> historic;


    public Park() {
        super();
    }

    public Park(Address address, String name, Customer customer) {
        this.address = address;
        this.name = name;
        this.customer = customer;
    }

    public Park(Address address, String name, String description) {
        this.address = address;
        this.name = name;
        this.description = description;
    }

    public Park(Address address, String name, String description, Integer spots, String phone, String email, Double evaluation, Customer customer, Set<Comment> comments, Set<Evaluation> evaluations, Set<Rate> rates, Set<Historic> historic) {
        this.address = address;
        this.name = name;
        this.description = description;
        this.spots = spots;
        this.phone = phone;
        this.email = email;
        this.evaluation = evaluation;
        this.customer = customer;
        this.comments = comments;
        this.evaluations = evaluations;
        this.rates = rates;
        this.historic = historic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSpots() {
        return spots;
    }

    public void setSpots(Integer spots) {
        this.spots = spots;
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

    public Double getEvaluation() {
        return evaluation;
    }

    public void setEvaluation() {
        int sum = 0;
        for (Evaluation evalu : evaluations) {
            sum = sum + evalu.getStarNumber();
        }
        this.evaluation = ((double) sum / (double) evaluations.size());
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public Set<Rate> getRates() {
        return rates;
    }

    public void setRates(Set<Rate> rates) {
        this.rates = rates;
    }

    public Set<Historic> getHistoric() {
        return historic;
    }

    public void setHistoric(Set<Historic> historic) {
        this.historic = historic;
    }
}
