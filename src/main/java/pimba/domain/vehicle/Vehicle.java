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

    private static final long serialVersionUID = -5899362660838917924L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String motor;

    private String brand;

    private Integer year;

    private String licensePlate;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Common common;

    public Vehicle() {
    }

    public Vehicle(String name, String motor, String brand, Integer year, String licensePlate, Common common) {
        this.name = name;
        this.motor = motor;
        this.brand = brand;
        this.year = year;
        this.licensePlate = licensePlate;
        this.common = common;
    }

    public Vehicle(String name, String motor, String brand, Integer year, String licensePlate) {
        this.name = name;
        this.motor = motor;
        this.brand = brand;
        this.year = year;
        this.licensePlate = licensePlate;
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

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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
