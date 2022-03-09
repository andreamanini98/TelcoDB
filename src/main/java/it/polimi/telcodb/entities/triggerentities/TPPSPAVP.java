package it.polimi.telcodb.entities.triggerentities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TotalPurchasesPerSPAndVP")
public class TPPSPAVP {

    @EmbeddedId
    private TTPSPAVPPrimaryKey pk;
    private int totalAmount;

    public TPPSPAVP() {
    }


    public TTPSPAVPPrimaryKey getPk() {
        return pk;
    }


    public int getTotalAmount() {
        return totalAmount;
    }

}
