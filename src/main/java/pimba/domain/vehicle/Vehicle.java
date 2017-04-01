package pimba.domain.vehicle;

import com.fasterxml.jackson.annotation.JsonBackReference;
import pimba.domain.common.Common;
import pimba.entities.EntityWithTimestamps;

import javax.persistence.*;

/**
 * Created by paulo on 19/03/17.
 */
@Entity
public class Vehicle extends EntityWithTimestamps {
    private static final long serialVersionUID = -6150305127167974080L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private String company;

    private Integer year;

    private String licensePlate;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Common common;

    public Vehicle(String name, String description, String company, Integer year, String licensePlate) {
        super();
        this.name = name;
        this.description = description;
        this.company = company;
        this.year = year;
        this.licensePlate = licensePlate;
    }

    public Vehicle(Common common) {
        this.common = common;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

}
