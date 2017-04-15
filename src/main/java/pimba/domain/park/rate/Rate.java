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
    private RatePeriod period;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Park park;

    public Rate() {
        super();
    }

    public Rate(Double price, RatePeriod period, Park park) {
        this.price = price;
        this.period = period;
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
        return period;
    }

    public void setPeriod(RatePeriod period) {
        this.period = period;
    }

    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }
}
