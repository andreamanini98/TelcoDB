package it.polimi.telcodb.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;

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
    private Time hourOfRejection;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userUsernameFK")
    private User user;

    public Alert() {
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

    public Time getHourOfRejection() {
        return hourOfRejection;
    }

    public void setHourOfRejection(Time hourOfRejection) {
        this.hourOfRejection = hourOfRejection;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
