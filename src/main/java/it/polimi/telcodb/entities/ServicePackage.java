package it.polimi.telcodb.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class ServicePackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "servicePackages", fetch = FetchType.LAZY)
    private List<User> users;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name = "ServicePackageJTService",
            joinColumns = @JoinColumn(name = "servicePackageIdFK"),
            inverseJoinColumns = @JoinColumn(name = "serviceIdFK"))
    private List<Service> services;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name = "ServicePackageJTValidityPeriod",
            joinColumns = @JoinColumn(name = "servicePackageIdFK"),
            inverseJoinColumns = @JoinColumn(name = "validityPeriodIdFK"))
    private List<ValidityPeriod> validityPeriods;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name = "ServicePackageJTOptionalProduct",
            joinColumns = @JoinColumn(name = "servicePackageIdFK"),
            inverseJoinColumns = @JoinColumn(name = "optionalProductIdFK"))
    private List<OptionalProduct> optionalProducts;

    @OneToMany(mappedBy = "servicePackage", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserOrder> userOrders;

    public ServicePackage() {
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public List<User> getUsers() {
        return users;
    }


    public void setUsers(List<User> users) {
        this.users = users;
    }


    public List<Service> getServices() {
        return services;
    }


    public void setServices(List<Service> services) {
        this.services = services;
    }


    public List<ValidityPeriod> getValidityPeriods() {
        return validityPeriods;
    }


    public void setValidityPeriods(List<ValidityPeriod> validityPeriods) {
        this.validityPeriods = validityPeriods;
    }


    public List<OptionalProduct> getOptionalProducts() {
        return optionalProducts;
    }


    public void setOptionalProducts(List<OptionalProduct> optionalProducts) {
        this.optionalProducts = optionalProducts;
    }


    public List<UserOrder> getUserOrders() {
        return userOrders;
    }


    public void setUserOrders(List<UserOrder> userOrders) {
        this.userOrders = userOrders;
    }

}
