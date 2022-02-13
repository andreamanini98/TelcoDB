package it.polimi.telcodb.services;

import it.polimi.telcodb.entities.*;
import it.polimi.telcodb.enums.ServiceType;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class QueryService {

    @PersistenceContext
    private EntityManager entityManager;


    public List<OptionalProduct> findAllOptionalProducts() {
        return entityManager.createNamedQuery("OptionalProduct.findAll", OptionalProduct.class).getResultList();
    }


    public List<ValidityPeriod> findAllValidityPeriods() {
        return entityManager.createNamedQuery("ValidityPeriod.findAll", ValidityPeriod.class).getResultList();
    }


    public List<ServicePackage> findAllServicePackages() {
        return entityManager.createNamedQuery("ServicePackage.findAll", ServicePackage.class).getResultList();
    }


    public List<TelcoService> findServiceByServiceType(ServiceType serviceType) {
        return entityManager.createNamedQuery("TelcoService.findByServiceType", TelcoService.class)
                .setParameter(1, serviceType).getResultList();
    }


    public boolean getIsInsolventByUsername(String username) {
        return entityManager.createNamedQuery("User.getIsInsolventByUsername", Boolean.class)
                .setParameter(1, username).getSingleResult();
    }


    @Transactional
    public List<UserOrder> getInvalidOrdersByUsername(String username) {
        return entityManager.createNamedQuery("UserOrder.getInvalidOrdersByUser", UserOrder.class)
                .setParameter(1, entityManager.find(User.class, username)).getResultList();
    }


    @Transactional
    public BigDecimal getSumOfAllInvalidOrdersCostByUsername(String username) {
        return entityManager.createNamedQuery("UserOrder.getSumOfAllInvalidOrdersCostByUser", BigDecimal.class)
                .setParameter(1, entityManager.find(User.class, username)).getSingleResult();
    }


    @Transactional
    public ServicePackage findServicePackageById(String id) {
        return entityManager.find(ServicePackage.class, Long.parseLong(id));
    }


    @Transactional
    public UserOrder findUserOrderById(String id) {
        return entityManager.find(UserOrder.class, Long.parseLong(id));
    }

}
