package it.polimi.telcodb.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dateOfCreation;
    @Temporal(TemporalType.TIME)
    private Date hourOfCreation;
    private BigDecimal totalValue;
    @Temporal(TemporalType.DATE)
    private Date startDateOfSubscription;
    @Column(columnDefinition = "TINYINT", length = 1)
    private boolean isValid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userUsernameFK")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "servicePackageIdFK")
    private ServicePackage servicePackage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "validityPeriodIdFK")
    private ValidityPeriod validityPeriod;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "userOrderJTOptionalProduct",
            joinColumns = @JoinColumn(name = "userOrderIdFK"),
            inverseJoinColumns = @JoinColumn(name = "optionalProductIdFK"))
    private List<OptionalProduct> optionalProducts;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "activationScheduleIdFK")
    private ActivationSchedule activationSchedule;

    public UserOrder() {
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Date getDateOfCreation() {
        return dateOfCreation;
    }


    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }


    public Date getHourOfCreation() {
        return hourOfCreation;
    }


    public void setHourOfCreation(Date hourOfCreation) {
        this.hourOfCreation = hourOfCreation;
    }


    public BigDecimal getTotalValue() {
        return totalValue;
    }


    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }


    public Date getStartDateOfSubscription() {
        return startDateOfSubscription;
    }


    public void setStartDateOfSubscription(Date startDateOfSubscription) {
        this.startDateOfSubscription = startDateOfSubscription;
    }


    public boolean isValid() {
        return isValid;
    }


    public void setValid(boolean valid) {
        isValid = valid;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public ServicePackage getServicePackage() {
        return servicePackage;
    }


    public void setServicePackage(ServicePackage servicePackage) {
        this.servicePackage = servicePackage;
    }


    public ValidityPeriod getValidityPeriod() {
        return validityPeriod;
    }


    public void setValidityPeriod(ValidityPeriod validityPeriod) {
        this.validityPeriod = validityPeriod;
    }


    public List<OptionalProduct> getOptionalProducts() {
        return optionalProducts;
    }


    public void setOptionalProducts(List<OptionalProduct> optionalProducts) {
        this.optionalProducts = optionalProducts;
    }


    public ActivationSchedule getActivationSchedule() {
        return activationSchedule;
    }


    public void setActivationSchedule(ActivationSchedule activationSchedule) {
        this.activationSchedule = activationSchedule;
    }

}
