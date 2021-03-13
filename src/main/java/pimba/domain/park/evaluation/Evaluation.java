package pimba.domain.park.evaluation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import pimba.domain.common.Common;
import pimba.domain.park.Park;
import pimba.entities.EntityWithTimestamps;

import javax.persistence.*;

/**
 * Created by paulo on 14/04/17.
 */
@Entity
public class Evaluation extends EntityWithTimestamps {
    private static final long serialVersionUID = 5895066379537433375L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer starNumber;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Common common;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Park park;

    public Evaluation() {
        super();
    }

    public Evaluation(Integer starNumber, Common common, Park park) {
        this.starNumber = starNumber;
        this.common = common;
        this.park = park;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStarNumber() {
        return starNumber;
    }

    public void setStarNumber(Integer starNumber) {
        this.starNumber = starNumber;
    }

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }
}
