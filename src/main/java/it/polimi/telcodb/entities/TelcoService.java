package it.polimi.telcodb.entities;

import it.polimi.telcodb.enums.ServiceType;
import it.polimi.telcodb.enums.ServiceTypeConverter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Service")
@NamedQuery(name = "TelcoService.findByServiceType", query = "SELECT s FROM TelcoService s WHERE s.serviceType = ?1")
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
        this.serviceType = ServiceType.FIXED_PHONE;
    }

    public TelcoService(int numberOfMinutes, int numberOfSMSs, BigDecimal extraMinutesFee, BigDecimal extraSMSFee) {
        this.serviceType = ServiceType.MOBILE_PHONE;
        this.numberOfMinutes = numberOfMinutes;
        this.numberOfSMSs = numberOfSMSs;
        this.extraMinutesFee = extraMinutesFee;
        this.extraSMSsFee = extraSMSFee;
    }

    public TelcoService(ServiceType serviceType, int numberOfGigabytes, BigDecimal extraGigabytesFee) {
        switch (serviceType) {
            case FIXED_INTERNET:
                this.serviceType = ServiceType.FIXED_INTERNET;
                break;

            case MOBILE_INTERNET:
                this.serviceType = ServiceType.MOBILE_INTERNET;
                break;

            default:
                this.serviceType = null;
        }
        this.numberOfGigabytes = numberOfGigabytes;
        this.extraGigabytesFee = extraGigabytesFee;
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


    @Override
    public String toString() {
        switch (this.serviceType) {
            case FIXED_PHONE:
                return Long.toString(id) + " " + serviceType;

            case MOBILE_PHONE:
                return Long.toString(id) + " " + serviceType + ": "
                        + numberOfMinutes + " minutes - " + numberOfSMSs + " SMSs - "
                        + extraMinutesFee + " €/extraMinute - " + extraGigabytesFee + " €/extraGigabyte";

            case FIXED_INTERNET:
            case MOBILE_INTERNET:
                return Long.toString(id) + " " + serviceType + ": " + numberOfGigabytes
                        + " gigabytes - " + extraGigabytesFee + " €/extraGigabyte";

            default:
                return "";
        }
    }

}
