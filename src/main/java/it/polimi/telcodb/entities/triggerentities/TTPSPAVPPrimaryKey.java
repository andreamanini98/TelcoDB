package it.polimi.telcodb.entities.triggerentities;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TTPSPAVPPrimaryKey implements Serializable {

    protected Long spId;
    protected Long vpId;

    public TTPSPAVPPrimaryKey() {
    }

    public Long getSpId() {
        return spId;
    }


    public void setSpId(Long spId) {
        this.spId = spId;
    }


    public Long getVpId() {
        return vpId;
    }


    public void setVpId(Long vpId) {
        this.vpId = vpId;
    }

}
