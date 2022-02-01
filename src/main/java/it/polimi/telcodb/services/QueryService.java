package it.polimi.telcodb.services;

import it.polimi.telcodb.entities.OptionalProduct;
import it.polimi.telcodb.entities.TelcoService;
import it.polimi.telcodb.entities.ValidityPeriod;
import it.polimi.telcodb.enums.ServiceType;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class QueryService {

    @PersistenceContext
    EntityManager entityManager;


    public List<OptionalProduct> findAllOptionalProducts() {
        return entityManager.createNamedQuery("OptionalProduct.findAll", OptionalProduct.class).getResultList();
    }


    public List<ValidityPeriod> findAllValidityPeriod() {
        return entityManager.createNamedQuery("ValidityPeriod.findAll", ValidityPeriod.class).getResultList();
    }


    public List<TelcoService> findServiceByServiceType(ServiceType serviceType) {
        return entityManager.createNamedQuery("TelcoService.findByServiceType", TelcoService.class).setParameter(1, serviceType).getResultList();
    }

}
