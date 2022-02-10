package it.polimi.telcodb.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@NamedQuery(name = "OptionalProduct.findAll", query = "SELECT o FROM OptionalProduct o")
public class OptionalProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal monthlyFee;

    @ManyToMany(mappedBy = "optionalProducts", fetch = FetchType.LAZY)
    private List<ServicePackage> servicePackages;

    @ManyToMany(mappedBy = "optionalProducts", fetch = FetchType.LAZY)
    private List<UserOrder> userOrders;

    public OptionalProduct() {
    }

    public OptionalProduct(String name, BigDecimal monthlyFee) {
        this.name = name;
        this.monthlyFee = monthlyFee;
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


    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }


    public void setMonthlyFee(BigDecimal monthlyFee) {
        this.monthlyFee = monthlyFee;
    }


    public List<ServicePackage> getServicePackages() {
        return servicePackages;
    }


    public void setServicePackages(List<ServicePackage> servicePackages) {
        this.servicePackages = servicePackages;
    }


    public List<UserOrder> getUserOrders() {
        return userOrders;
    }


    public void setUserOrders(List<UserOrder> userOrders) {
        this.userOrders = userOrders;
    }


    @Override
    public String toString() {
        return name + " - " + monthlyFee + "€/month";
    }

}
