package pimba.domain.customer.promotion;

import com.fasterxml.jackson.annotation.JsonBackReference;
import pimba.domain.customer.Customer;
import pimba.entities.EntityWithTimestamps;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by paulo on 14/04/17.
 */
@Entity
public class Promotion extends EntityWithTimestamps {
    private static final long serialVersionUID = -2934281929106424885L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double price;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private PromotionPeriod period;

    private Integer amountOfPeriod;

    private Date expiration;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Customer customer;

    public Promotion() {
        super();
    }

    public Promotion(Double price, String name, String description, PromotionPeriod period, Integer amountOfPeriod, Date expiration, Customer customer) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.period = period;
        this.amountOfPeriod = amountOfPeriod;
        this.expiration = expiration;
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

    public PromotionPeriod getPeriod() {
        return period;
    }

    public void setPeriod(PromotionPeriod period) {
        this.period = period;
    }

    public Integer getAmountOfPeriod() {
        return amountOfPeriod;
    }

    public void setAmountOfPeriod(Integer amountOfPeriod) {
        this.amountOfPeriod = amountOfPeriod;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
