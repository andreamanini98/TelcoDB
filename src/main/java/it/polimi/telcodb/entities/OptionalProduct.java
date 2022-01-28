package it.polimi.telcodb.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class OptionalProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal monthlyFee;

    @ManyToMany(mappedBy = "optionalProducts", fetch = FetchType.LAZY)
    private List<ServicePackage> servicePackages;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "validityPeriodIdFK")
    private ValidityPeriod validityPeriod;

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


    public ValidityPeriod getValidityPeriod() {
        return validityPeriod;
    }


    public void setValidityPeriod(ValidityPeriod validityPeriod) {
        this.validityPeriod = validityPeriod;
    }


    public List<UserOrder> getUserOrders() {
        return userOrders;
    }


    public void setUserOrders(List<UserOrder> userOrders) {
        this.userOrders = userOrders;
    }

}
