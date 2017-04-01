package pimba.domain.address;

import javax.persistence.*;

/**
 * Created by joao on 01/12/16.
 */
@Entity
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn
    private Country country;

    public State() { super(); }

    public State(String name, Country country) {
        setName(name);
        setCountry(country);
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
