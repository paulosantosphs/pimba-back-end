package pimba.domain.common.historic;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import pimba.domain.common.Common;
import pimba.domain.park.Park;
import pimba.entities.EntityWithTimestamps;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by paulo on 11/05/17.
 */

@Entity
public class Historic extends EntityWithTimestamps {


    private static final long serialVersionUID = 8121374442879694773L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double payment;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private Boolean botton;

    @ManyToOne
    @JoinColumn
    private Park park;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Common common;

    public Historic() {
    }

    public Historic(Double payment, Boolean botton, Park park, Common common) {
        this.payment = payment;
        this.date = new Date();
        this.botton = botton;
        this.park = park;
        this.common = common;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate() {
        this.date = new Date();
    }

    public Boolean getBotton() {
        return botton;
    }

    public void setBotton(Boolean botton) {
        this.botton = botton;
    }

    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }
}
