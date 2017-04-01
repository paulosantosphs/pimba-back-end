package pimba.domain.park;

import com.fasterxml.jackson.annotation.JsonBackReference;
import pimba.domain.address.Address;
import pimba.domain.customer.Customer;
import pimba.entities.EntityWithTimestamps;

import javax.persistence.*;

/**
 * Created by paulo on 19/03/17.
 */
@Entity
public class Park extends EntityWithTimestamps {

    private static final long serialVersionUID = -4147787214552385471L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn
    private Address address;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Customer customer;

    public Park(Address address, String name, String description, Customer customer) {
        super();
        this.address = address;
        this.name = name;
        this.description = description;
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
