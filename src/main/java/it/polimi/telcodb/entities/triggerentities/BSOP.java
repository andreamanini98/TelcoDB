package it.polimi.telcodb.entities.triggerentities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "BestSellerOptionalProduct")
@NamedQuery(name = "BSOP.findAll", query = "SELECT b FROM BSOP b")
public class BSOP {

    @Id
    private Long opId;

    public BSOP() {
    }


    public Long getOpId() {
        return opId;
    }

}
