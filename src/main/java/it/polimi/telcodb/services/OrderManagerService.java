package it.polimi.telcodb.services;

import it.polimi.telcodb.entities.ActivationSchedule;
import it.polimi.telcodb.entities.Alert;
import it.polimi.telcodb.entities.User;
import it.polimi.telcodb.entities.UserOrder;
import it.polimi.telcodb.model.ServicePackageOrder;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;

@Service
public class OrderManagerService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private QueryService queryService;


    @Transactional
    public boolean createOrderAndAlert(String username, ServicePackageOrder spO, boolean isValid) {
        createOrder(username, spO, isValid);
        return createAlert(username);
    }


    @Transactional
    public boolean tryPaymentAgainAndAlert(String username, String orderId, boolean isOrderValid) {
        tryPaymentAgain(username, orderId, isOrderValid);
        return createAlert(username);
    }


    @Transactional
    public void createOrder(String username, ServicePackageOrder spO, boolean isOrderValid) {
        User user = entityManager.find(User.class, username);
        UserOrder order = new UserOrder();

        user.setInsolvent(!isOrderValid);
        if (!isOrderValid) user.setFailedPayments(user.getFailedPayments() + 1);

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


    @Transactional
    public void tryPaymentAgain(String username, String orderId, boolean isOrderValid) {
        UserOrder order = entityManager.find(UserOrder.class, Long.parseLong(orderId));
        User user = entityManager.find(User.class, username);

        if (!isOrderValid) {
            user.setFailedPayments(user.getFailedPayments() + 1);
        } else {
            ActivationSchedule activationSchedule = new ActivationSchedule();
            activationSchedule.setStartDate(order.getStartDateOfSubscription());
            activationSchedule.setEndDate(
                    DateUtils.addMonths(
                            order.getStartDateOfSubscription(),
                            order.getValidityPeriod().getNumberOfMonths()
                    )
            );
            order.setActivationSchedule(activationSchedule);
            order.setValid(true);
            if (queryService.getNumberOfInvalidOrdersByUsername(username) == 0)
                user.setInsolvent(false);
        }
    }


    @Transactional
    public boolean createAlert(String username) {
        boolean isAlertCreated = false;
        User user = entityManager.find(User.class, username);

        if (user.getFailedPayments() == 3) {
            isAlertCreated = true;
            Alert alert = new Alert(
                    queryService.getSumOfAllInvalidOrdersCostByUsername(user.getUsername()),
                    user, user.getEmail(), new Date(), new Date()
            );
            user.setFailedPayments(0);
            entityManager.persist(alert);
        }
        return isAlertCreated;
    }

}
