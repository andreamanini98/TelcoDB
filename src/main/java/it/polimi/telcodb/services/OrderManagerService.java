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


    @Transactional
    public void createOrder(String username, ServicePackageOrder spO) {
        User user = entityManager.find(User.class, username);
        UserOrder order = new UserOrder();

        order.setUser(user);
        order.setDateOfCreation(new Date());
        order.setHourOfCreation(new Date());
        order.setServicePackage(spO.getServicePackage());
        order.setValidityPeriod(spO.getValidityPeriod());
        order.setOptionalProducts(spO.getOptionalProducts());
        order.setTotalValue(spO.computeTotalCost());
        order.setStartDateOfSubscription(spO.getSubscriptionDateWrapper().getDate());
        order.setValid(true);

        ActivationSchedule activationSchedule = new ActivationSchedule();
        activationSchedule.setStartDate(spO.getSubscriptionDateWrapper().getDate());
        activationSchedule.setEndDate(
                DateUtils.addMonths(
                        spO.getSubscriptionDateWrapper().getDate(),
                        spO.getValidityPeriod().getNumberOfMonths()
                )
        );
        order.setActivationSchedule(activationSchedule);

        entityManager.persist(order);
    }

}
