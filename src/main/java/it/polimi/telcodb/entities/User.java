package it.polimi.telcodb.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = "User.getIsInsolventByUsername", query = "SELECT u.isInsolvent FROM User u WHERE u.username = ?1")
@NamedQuery(name = "User.findAllInsolvents", query = "SELECT u FROM User u WHERE u.isInsolvent = 1")
public class User {

    @Id
    private String username;
    private String email;
    private String password;
    @Column(columnDefinition = "TINYINT", length = 1)
    private boolean isInsolvent;
    private int failedPayments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Alert> alerts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserOrder> userOrders;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isInsolvent = false;
        this.failedPayments = 0;
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


    public int getFailedPayments() {
        return failedPayments;
    }


    public void setFailedPayments(int failedPayments) {
        this.failedPayments = failedPayments;
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


    public List<UserOrder> getUserOrders() {
        return userOrders;
    }


    public void setUserOrders(List<UserOrder> orders) {
        this.userOrders = orders;
    }

}
