package it.polimi.telcodb.services;

import it.polimi.telcodb.entities.ActivationSchedule;
import it.polimi.telcodb.entities.User;
import it.polimi.telcodb.entities.UserOrder;
import it.polimi.telcodb.model.ServicePackageOrder;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;

@Service
public class OrderManagerService {

    @PersistenceContext
    private EntityManager entityManager;

    // TODO per resettare l'insolvent a false puoi fare un metodo a parte che prende solo i servizi di cui l'utente
    //      non è riuscito a pagare

    @Transactional
    public void createOrder(String username, ServicePackageOrder spO, boolean isOrderValid) {
        User user = entityManager.find(User.class, username);
        UserOrder order = new UserOrder();

        user.setInsolvent(!isOrderValid);
        if (!isOrderValid)
            user.setFailedPayments(user.getFailedPayments() + 1);
        if (user.getFailedPayments() == 3) {
            // TODO handle alert generation
            user.setFailedPayments(0);
        }

        order.setUser(user);
        order.setDateOfCreation(new Date());
        order.setHourOfCreation(new Date());
        order.setServicePackage(spO.getServicePackage());
        order.setValidityPeriod(spO.getValidityPeriod());
        order.setOptionalProducts(spO.getOptionalProducts());
        order.setTotalValue(spO.computeTotalCost());
        order.setStartDateOfSubscription(spO.getSubscriptionDateWrapper().getDate());
        order.setValid(isOrderValid);

        if (isOrderValid) {
            ActivationSchedule activationSchedule = new ActivationSchedule();
            activationSchedule.setStartDate(spO.getSubscriptionDateWrapper().getDate());
            activationSchedule.setEndDate(
                    DateUtils.addMonths(
                            spO.getSubscriptionDateWrapper().getDate(),
                            spO.getValidityPeriod().getNumberOfMonths()
                    )
            );
            order.setActivationSchedule(activationSchedule);
        }

        entityManager.persist(order);
    }

}
