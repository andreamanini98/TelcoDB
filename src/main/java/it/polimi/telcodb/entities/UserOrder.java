package it.polimi.telcodb.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@Entity
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dateOfCreation;
    @Temporal(TemporalType.TIME)
    private Time hourOfCreation;
    private BigDecimal totalValue;
    @Temporal(TemporalType.DATE)
    private Date startDateOfSubscription;
    @Column(columnDefinition = "TINYINT", length = 1)
    private boolean isRejected;
    @Column(columnDefinition = "TINYINT", length = 1)
    private boolean isValid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userUsernameFK")
    private User user;

    public UserOrder() {
    }

}
