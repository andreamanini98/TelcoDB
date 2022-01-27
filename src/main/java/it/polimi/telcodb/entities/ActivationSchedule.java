package it.polimi.telcodb.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ActivationSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @OneToOne(mappedBy = "activationSchedule", fetch = FetchType.EAGER)
    private UserOrder userOrder;

    public ActivationSchedule() {
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Date getStartDate() {
        return startDate;
    }


    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    public Date getEndDate() {
        return endDate;
    }


    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public UserOrder getUserOrder() {
        return userOrder;
    }


    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }

}
