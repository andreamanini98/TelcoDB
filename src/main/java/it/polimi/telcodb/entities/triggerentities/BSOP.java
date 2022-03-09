package it.polimi.telcodb.entities.triggerentities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BestSellerOptionalProduct")
public class BSOP {

    @Id
    private Long opId;

    public BSOP() {
    }


    public Long getOpId() {
        return opId;
    }

}
