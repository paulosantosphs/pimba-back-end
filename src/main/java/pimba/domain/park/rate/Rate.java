package pimba.domain.park.rate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import pimba.domain.park.Park;
import pimba.entities.EntityWithTimestamps;

import javax.persistence.*;

/**
 * Created by paulo on 14/04/17.
 */
@Entity
public class Rate extends EntityWithTimestamps {
    private static final long serialVersionUID = 2763755172073093773L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double price;

    @Enumerated(EnumType.STRING)
    private RatePeriod ratePeriod;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Park park;

    public Rate() {
        super();
    }

    public Rate(Park park) {
        this.park = park;
    }

    public Rate(Double price, RatePeriod ratePeriod, Park park) {
        this.price = price;
        this.ratePeriod = ratePeriod;
        this.park = park;
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

    public RatePeriod getPeriod() {
        return ratePeriod;
    }

    public void setPeriod(RatePeriod ratePeriod) {
        this.ratePeriod = ratePeriod;
    }

    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }
}
