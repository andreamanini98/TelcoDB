package it.polimi.telcodb.services;

import it.polimi.telcodb.entities.Alert;
import it.polimi.telcodb.entities.User;
import it.polimi.telcodb.entities.UserOrder;
import it.polimi.telcodb.entities.triggerentities.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class InspectionPageService {

    @PersistenceContext
    private EntityManager entityManager;


    public List<ANOOPWSP> findAllANOOPWSPs() {
        return entityManager.createNamedQuery("ANOOPWSP.findAll", ANOOPWSP.class).getResultList();
    }


    public List<BSOP> findAllBSOPs() {
        return entityManager.createNamedQuery("BSOP.findAll", BSOP.class).getResultList();
    }


    public List<TPPSP> findAllTPPSPs() {
        return entityManager.createNamedQuery("TPPSP.findAll", TPPSP.class).getResultList();
    }


    public List<TPPSPAVP> findAllTPPSPAVPs() {
        return entityManager.createNamedQuery("TPPSPAVP.findAll", TPPSPAVP.class).getResultList();
    }


    public List<TSVOSP> findAllTSVOSPs() {
        return entityManager.createNamedQuery("TSVOSP.findAll", TSVOSP.class).getResultList();
    }


    public List<User> findAllInsolvents() {
        return entityManager.createNamedQuery("User.findAllInsolvents", User.class).getResultList();
    }


    public List<UserOrder> findAllInvalids() {
        return entityManager.createNamedQuery("UserOrder.findAllInvalids", UserOrder.class).getResultList();
    }


    public List<Alert> findAllAlerts() {
        return entityManager.createNamedQuery("Alert.findAll", Alert.class).getResultList();
    }

}
