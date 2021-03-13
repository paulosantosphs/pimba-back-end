package pimba.login.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.StringUtils;
import pimba.domain.common.Common;
import pimba.domain.customer.Customer;
import pimba.login.model.passport.Passport;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class User implements Serializable {


    private static final long serialVersionUID = 148393034858873300L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @JsonIgnore
    private Date insertedDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @JsonIgnore
    private Date updatedDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    @OneToOne
    @JoinColumn
    private Common common;

    @OneToOne
    @JoinColumn
    private Customer customer;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Passport> passports;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @PrePersist
    protected void onCreate() {
        insertedDate = updatedDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
    }

    public User(String email, Common common) {
        super();
        this.email = email;
        this.common = common;
    }

    public User(String email, Customer customer) {
        super();
        this.email = email;
        this.customer = customer;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", insertedDate=" + insertedDate + ", updatedDate=" + updatedDate
                + ", passports=" + passports.toString() + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin() {
        this.lastLogin = new Date();
    }

    public Date getInsertedDate() {
        return insertedDate;
    }

    public void setInsertedDate(Date insertedDate) {
        this.insertedDate = insertedDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public List<Passport> getPassports() {
        return passports;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setPassports(List<Passport> passports) {
        this.passports = passports;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @JsonIgnore
    public String getRolesCommaSepareted() {
        List<String> authorities = roles.stream().map(role -> role.getName()).collect(Collectors.toList());

        return StringUtils.arrayToDelimitedString(authorities.toArray(), ",");
    }

}
