package it.polimi.telcodb.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private String email;
    @Temporal(TemporalType.DATE)
    private Date dateOfRejection;
    @Temporal(TemporalType.TIME)
    private Date hourOfRejection;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userUsernameFK")
    private User user;

    public Alert() {
    }

    public Alert(BigDecimal amount, User user, String email, Date dateOfRejection, Date hourOfRejection) {
        this.amount = amount;
        this.user = user;
        this.email = email;
        this.dateOfRejection = dateOfRejection;
        this.hourOfRejection = hourOfRejection;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public BigDecimal getAmount() {
        return amount;
    }


    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public Date getDateOfRejection() {
        return dateOfRejection;
    }


    public void setDateOfRejection(Date dateOfRejection) {
        this.dateOfRejection = dateOfRejection;
    }


    public Date getHourOfRejection() {
        return hourOfRejection;
    }


    public void setHourOfRejection(Date hourOfRejection) {
        this.hourOfRejection = hourOfRejection;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

}
