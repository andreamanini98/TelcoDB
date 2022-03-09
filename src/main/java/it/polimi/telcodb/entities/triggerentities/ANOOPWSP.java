package it.polimi.telcodb.entities.triggerentities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AverageNumberOfOPWithSP")
public class ANOOPWSP {

    @Id
    private Long spId;
    private float averageOPs;

    public ANOOPWSP() {
    }


    public Long getSpId() {
        return spId;
    }


    public float getAverageOPs() {
        return averageOPs;
    }

}
