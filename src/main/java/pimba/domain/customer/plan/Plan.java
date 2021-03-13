package pimba.domain.customer.plan;

import com.fasterxml.jackson.annotation.JsonBackReference;
import pimba.domain.customer.Customer;
import pimba.entities.EntityWithTimestamps;

import javax.persistence.*;

/**
 * Created by paulo on 14/04/17.
 */
@Entity
public class Plan extends EntityWithTimestamps {
    private static final long serialVersionUID = -8795984770292334099L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double price;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private PlanPeriod period;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Customer customer;


    public Plan() {
        super();
    }

    public Plan(Double price, String name, String description, PlanPeriod period, Customer customer) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.period = period;
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public PlanPeriod getPeriod() {
        return period;
    }

    public void setPeriod(PlanPeriod period) {
        this.period = period;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
