package pimba.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by joao on 25/11/16.
 * Entidade para cuidar do created_date e updated_date
 */

@MappedSuperclass
public class EntityWithTimestamps implements Serializable {

    private static final long serialVersionUID = -5922212113015326486L;
    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @PrePersist
    public void onCreated() {
        this.createdDate = new Date();
        this.updatedDate = new Date();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedDate = new Date();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
