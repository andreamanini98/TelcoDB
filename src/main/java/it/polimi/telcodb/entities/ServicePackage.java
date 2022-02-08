package it.polimi.telcodb.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = "ServicePackage.findAll", query = "SELECT s FROM ServicePackage s")
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
    private List<TelcoService> services = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name = "ServicePackageJTValidityPeriod",
            joinColumns = @JoinColumn(name = "servicePackageIdFK"),
            inverseJoinColumns = @JoinColumn(name = "validityPeriodIdFK"))
    private List<ValidityPeriod> validityPeriods = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name = "ServicePackageJTOptionalProduct",
            joinColumns = @JoinColumn(name = "servicePackageIdFK"),
            inverseJoinColumns = @JoinColumn(name = "optionalProductIdFK"))
    private List<OptionalProduct> optionalProducts = new ArrayList<>();

    @OneToMany(mappedBy = "servicePackage", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserOrder> userOrders;

    public ServicePackage() {
    }

    public ServicePackage(String name) {
        this.name = name;
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


    public List<TelcoService> getServices() {
        return services;
    }


    public void setServices(List<TelcoService> services) {
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
