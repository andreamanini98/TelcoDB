package it.polimi.telcodb.entities;

import it.polimi.telcodb.enums.ServiceType;
import it.polimi.telcodb.enums.ServiceTypeConverter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = ServiceTypeConverter.class)
    private ServiceType serviceType;
    private int numberOfMinutes;
    private int numberOfSMSs;
    private BigDecimal extraSMSsFee;
    private BigDecimal extraMinutesFee;
    private int numberOfGigabytes;
    private BigDecimal extraGigabytesFee;

    public Service() {
    }

}
