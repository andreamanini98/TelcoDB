package it.polimi.telcodb.entities.triggerentities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "TotalSalesValueOfSP")
@NamedQuery(name = "TSVOSP.findAll", query = "SELECT t FROM TSVOSP t")
public class TSVOSP {

    @Id
    private Long spId;
    private BigDecimal totValueWithOP;
    private BigDecimal totValueWithoutOP;

    public TSVOSP() {
    }


    public Long getSpId() {
        return spId;
    }


    public BigDecimal getTotValueWithOP() {
        return totValueWithOP;
    }


    public BigDecimal getTotValueWithoutOP() {
        return totValueWithoutOP;
    }

}
