package it.polimi.telcodb.services;

import it.polimi.telcodb.entities.OptionalProduct;
import it.polimi.telcodb.entities.ValidityPeriod;
import it.polimi.telcodb.model.DateWrapper;
import it.polimi.telcodb.model.ServicePackageOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class SessionService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private QueryService queryService;

    // TODO pensa come rimuovere dalla sessione gli oggetti che ci metti dentro quando non serviranno più

    public void addServicePackageToServicePackageOrder(String servicePackageId, HttpSession session) {
        getServicePackageOrder(session).setServicePackage(queryService.findServicePackageById(servicePackageId));
    }


    public void resetServicePackageOrder(HttpSession session) {
        session.removeAttribute("spO");
        session.setAttribute("spO", new ServicePackageOrder());
    }


    public boolean isServicePackageAlreadySelected(HttpSession session) {
        return getServicePackageOrder(session).getServicePackage() != null;
    }


    // TODO la validità degli optional products non può essere settata come singola colonna
    //      forse devi fare una coppia ValidityPeriod/Utente
    //      Ma se fai così è un'informazione ridondante perché la puoi ricavare semplicemente dal validity period che l'utente ha scelto

    @Transactional
    public ServicePackageOrder addSelectedItemsToServicePackageOrder(HttpSession session, List<String> selectedOP, String validityPeriod, String subscriptionDate) {
        ServicePackageOrder spO = getServicePackageOrder(session);
        List<OptionalProduct> optionalProducts = new ArrayList<>();

        ValidityPeriod vp = entityManager.find(ValidityPeriod.class, Long.parseLong(validityPeriod));
        spO.setValidityPeriod(vp);

        DateWrapper dateWrapper = new DateWrapper(subscriptionDate);
        spO.setSubscriptionDateWrapper(dateWrapper);

        if (selectedOP != null) {
            for (String s : selectedOP) {
                //
                OptionalProduct op = entityManager.find(OptionalProduct.class, Long.parseLong(s));
                //
                op.setValidityPeriod(vp);
                optionalProducts.add(op);
            }
        }
        spO.setOptionalProducts(optionalProducts);

        return spO;
    }


    public ServicePackageOrder getServicePackageOrder(HttpSession session) {
        ServicePackageOrder spO = (ServicePackageOrder) session.getAttribute("spO");
        if (spO == null) {
            spO = new ServicePackageOrder();
            session.setAttribute("spO", spO);
        }
        return spO;
    }

}
