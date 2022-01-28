package it.polimi.telcodb.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class ValidityPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numberOfMonths;
    private BigDecimal monthlyFee;

    @ManyToMany(mappedBy = "validityPeriods", fetch = FetchType.LAZY)
    private List<ServicePackage> servicePackages;

    @OneToMany(mappedBy = "validityPeriod", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OptionalProduct> optionalProducts;

    @OneToMany(mappedBy = "validityPeriod", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserOrder> userOrders;

    public ValidityPeriod() {
    }

    public ValidityPeriod(int numberOfMonths, BigDecimal monthlyFee) {
        this.numberOfMonths = numberOfMonths;
        this.monthlyFee = monthlyFee;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public int getNumberOfMonths() {
        return numberOfMonths;
    }


    public void setNumberOfMonths(int numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
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
