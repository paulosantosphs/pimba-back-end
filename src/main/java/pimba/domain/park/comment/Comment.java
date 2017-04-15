package pimba.domain.park.comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import pimba.domain.common.Common;
import pimba.domain.park.Park;
import pimba.entities.EntityWithTimestamps;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by paulo on 14/04/17.
 */
@Entity
public class Comment extends EntityWithTimestamps {
    private static final long serialVersionUID = 4125133805636814929L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String comment;

    private Date date;

    @ManyToOne
    @JoinColumn
    private Common common;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Park park;

    public Comment() {
        super();
    }

    public Comment(String comment, Date date, Common common, Park park) {
        this.comment = comment;
        this.date = date;
        this.common = common;
        this.park = park;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
