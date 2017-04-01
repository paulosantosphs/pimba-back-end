package pimba.domain.address;

import javax.persistence.*;

/**
 * Created by joao on 01/12/16.
 */
@Entity
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn
    private City city;

    public District() {

    }

    public District(String name, City city) {
        setName(name);
        setCity(city);
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
