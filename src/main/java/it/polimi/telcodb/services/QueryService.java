package it.polimi.telcodb.services;

import it.polimi.telcodb.entities.OptionalProduct;
import it.polimi.telcodb.entities.ServicePackage;
import it.polimi.telcodb.entities.TelcoService;
import it.polimi.telcodb.entities.ValidityPeriod;
import it.polimi.telcodb.enums.ServiceType;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class QueryService {

    @PersistenceContext
    EntityManager entityManager;


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
        return entityManager.createNamedQuery("TelcoService.findByServiceType", TelcoService.class).setParameter(1, serviceType).getResultList();
    }


    //MAGARI VA TOLTO perché potresti trovare un modo per trovare le info dei SP senza toccare di nuovo il database?
    @Transactional
    public ServicePackage findServicePackageById(String id) {
        return entityManager.find(ServicePackage.class, Long.parseLong(id));
    }

}
