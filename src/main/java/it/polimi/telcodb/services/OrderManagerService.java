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
        UserOrder order = createOrder(username, spO, isValid);
        createActivationSchedule(order);
        return createAlert(username);
    }


    @Transactional
    public boolean tryPaymentAgainAndAlert(String username, String orderId, boolean isOrderValid) {
        UserOrder order = tryPaymentAgain(username, orderId, isOrderValid);
        createActivationSchedule(order);
        return createAlert(username);
    }


    @Transactional
    public UserOrder createOrder(String username, ServicePackageOrder spO, boolean isOrderValid) {
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

        entityManager.persist(order);
        entityManager.flush();
        return order;
    }


    @Transactional
    public UserOrder tryPaymentAgain(String username, String orderId, boolean isOrderValid) {
        UserOrder order = entityManager.find(UserOrder.class, Long.parseLong(orderId));
        User user = entityManager.find(User.class, username);

        if (!isOrderValid) {
            user.setFailedPayments(user.getFailedPayments() + 1);
        } else {
            order.setValid(true);
            if (queryService.getNumberOfInvalidOrdersByUsername(username) == 0)
                user.setInsolvent(false);
        }
        return order;
    }


    @Transactional
    public void createActivationSchedule(UserOrder order) {
        if (order.isValid()) {
            ActivationSchedule activationSchedule = new ActivationSchedule();
            activationSchedule.setStartDate(order.getStartDateOfSubscription());
            activationSchedule.setEndDate(
                    DateUtils.addMonths(
                            order.getStartDateOfSubscription(),
                            order.getValidityPeriod().getNumberOfMonths()
                    )
            );
            activationSchedule.setUserOrder(order);
            entityManager.persist(activationSchedule);
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
