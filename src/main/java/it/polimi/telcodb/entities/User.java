package it.polimi.telcodb.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    private String username;
    private String password;
    private String email;
    @Column(columnDefinition = "TINYINT", length = 1)
    private boolean isInsolvent;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Alert> alerts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserOrder> orders;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "UserJTServicePackage",
            joinColumns = @JoinColumn(name = "userUsernameFK"),
            inverseJoinColumns = @JoinColumn(name = "servicePackageIdFK"))
    private List<ServicePackage> servicePackages;

    public User() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isInsolvent() {
        return isInsolvent;
    }

    public void setInsolvent(boolean insolvent) {
        isInsolvent = insolvent;
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    public List<UserOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<UserOrder> orders) {
        this.orders = orders;
    }

    public List<ServicePackage> getServicePackages() {
        return servicePackages;
    }

    public void setServicePackages(List<ServicePackage> servicePackages) {
        this.servicePackages = servicePackages;
    }

}
