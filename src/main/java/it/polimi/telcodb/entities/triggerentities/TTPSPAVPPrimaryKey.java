package it.polimi.telcodb.entities.triggerentities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TTPSPAVPPrimaryKey that = (TTPSPAVPPrimaryKey) o;
        return spId.equals(that.spId) && vpId.equals(that.vpId);
    }


    @Override
    public int hashCode() {
        return Objects.hash(spId, vpId);
    }

}
