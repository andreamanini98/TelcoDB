package it.polimi.telcodb.model;

import it.polimi.telcodb.entities.OptionalProduct;
import it.polimi.telcodb.entities.ServicePackage;
import it.polimi.telcodb.entities.ValidityPeriod;

import java.util.List;

public class ServicePackageOrder {

    private ServicePackage servicePackage;
    private ValidityPeriod validityPeriod;
    private List<OptionalProduct> optionalProducts;
    private DateWrapper subscriptionDateWrapper;

    public ServicePackageOrder() {
        this.servicePackage = null;
        this.validityPeriod = null;
        this.optionalProducts = null;
        this.subscriptionDateWrapper = null;
    }


    public ServicePackage getServicePackage() {
        return servicePackage;
    }


    public void setServicePackage(ServicePackage servicePackage) {
        this.servicePackage = servicePackage;
    }


    public ValidityPeriod getValidityPeriod() {
        return validityPeriod;
    }


    public void setValidityPeriod(ValidityPeriod validityPeriod) {
        this.validityPeriod = validityPeriod;
    }


    public List<OptionalProduct> getOptionalProducts() {
        return optionalProducts;
    }


    public void setOptionalProducts(List<OptionalProduct> optionalProducts) {
        this.optionalProducts = optionalProducts;
    }


    public DateWrapper getSubscriptionDateWrapper() {
        return subscriptionDateWrapper;
    }


    public void setSubscriptionDateWrapper(DateWrapper subscriptionDateWrapper) {
        this.subscriptionDateWrapper = subscriptionDateWrapper;
    }

}
