package pimba.domain.address;

import pimba.entities.EntityWithTimestamps;

import javax.persistence.*;

/**
 * Created by joao on 01/12/16.
 */
@Entity
public class Address extends EntityWithTimestamps {

    private static final long serialVersionUID = -7968583706514602695L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double latitude;

    private Double longitude;

    private String number;

    private String street;

    @ManyToOne
    @JoinColumn
    private District district;


    public Address() {
        super();
    }

    public Address(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Address(Double latitude, Double longitude, String number, String street, District district) {
        super();
        setLatitude(latitude);
        setLongitude(longitude);
        setNumber(number);
        setStreet(street);
        setDistrict(district);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}
