package it.polimi.telcodb.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class ActivationSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;

    public ActivationSchedule() {
    }

}
