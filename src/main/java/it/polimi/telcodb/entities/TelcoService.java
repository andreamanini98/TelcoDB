package it.polimi.telcodb.entities;

import it.polimi.telcodb.enums.ServiceType;
import it.polimi.telcodb.enums.ServiceTypeConverter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Service")
public class TelcoService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = ServiceTypeConverter.class)
    private ServiceType serviceType;
    private Integer numberOfMinutes;
    private Integer numberOfSMSs;
    private BigDecimal extraSMSsFee;
    private BigDecimal extraMinutesFee;
    private Integer numberOfGigabytes;
    private BigDecimal extraGigabytesFee;

    @ManyToMany(mappedBy = "services", fetch = FetchType.LAZY)
    private List<ServicePackage> servicePackages;

    public TelcoService() {
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public ServiceType getServiceType() {
        return serviceType;
    }


    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }


    public int getNumberOfMinutes() {
        return numberOfMinutes;
    }


    public void setNumberOfMinutes(int numberOfMinutes) {
        this.numberOfMinutes = numberOfMinutes;
    }


    public int getNumberOfSMSs() {
        return numberOfSMSs;
    }


    public void setNumberOfSMSs(int numberOfSMSs) {
        this.numberOfSMSs = numberOfSMSs;
    }


    public BigDecimal getExtraSMSsFee() {
        return extraSMSsFee;
    }


    public void setExtraSMSsFee(BigDecimal extraSMSsFee) {
        this.extraSMSsFee = extraSMSsFee;
    }


    public BigDecimal getExtraMinutesFee() {
        return extraMinutesFee;
    }


    public void setExtraMinutesFee(BigDecimal extraMinutesFee) {
        this.extraMinutesFee = extraMinutesFee;
    }


    public int getNumberOfGigabytes() {
        return numberOfGigabytes;
    }


    public void setNumberOfGigabytes(int numberOfGigabytes) {
        this.numberOfGigabytes = numberOfGigabytes;
    }


    public BigDecimal getExtraGigabytesFee() {
        return extraGigabytesFee;
    }


    public void setExtraGigabytesFee(BigDecimal extraGigabytesFee) {
        this.extraGigabytesFee = extraGigabytesFee;
    }


    public List<ServicePackage> getServicePackages() {
        return servicePackages;
    }


    public void setServicePackages(List<ServicePackage> servicePackages) {
        this.servicePackages = servicePackages;
    }

}
