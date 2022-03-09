package it.polimi.telcodb.entities.triggerentities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TotalPurchasesPerSP")
public class TPPSP {

    @Id
    private Long spId;
    private int totalAmount;

    public TPPSP() {
    }

    public Long getSpId() {
        return spId;
    }


    public int getTotalAmount() {
        return totalAmount;
    }

}
