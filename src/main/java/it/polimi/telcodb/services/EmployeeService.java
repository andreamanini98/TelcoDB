package it.polimi.telcodb.services;

import it.polimi.telcodb.entities.*;
import it.polimi.telcodb.enums.ServiceType;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @PersistenceContext
    EntityManager entityManager;


    @Transactional
    public void saveEmployee(String username, String password, String email) {
        Employee employee = new Employee();
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setEmail(email);
        entityManager.persist(employee);
    }


    @Transactional
    public void saveFixedPhoneService() {
        TelcoService service = new TelcoService();
        entityManager.persist(service);
    }


    @Transactional
    public void saveMobilePhoneService(int nMinutes, int nSMSs, BigDecimal extraMinFee, BigDecimal extraGigaFee) {
        TelcoService service = new TelcoService(nMinutes, nSMSs, extraMinFee, extraGigaFee);
        entityManager.persist(service);
    }


    @Transactional
    public void saveFixedMobileInternetService(ServiceType serviceType, int nGiga, BigDecimal extraGigaFee) {
        TelcoService service = new TelcoService(serviceType, nGiga, extraGigaFee);
        entityManager.persist(service);
    }


    @Transactional
    public void saveValidityPeriod(int nMonths, BigDecimal monthlyFee) {
        ValidityPeriod validityPeriod = new ValidityPeriod(nMonths, monthlyFee);
        entityManager.persist(validityPeriod);
    }


    @Transactional
    public void saveOptionalProduct(String name, BigDecimal monthlyFee) {
        OptionalProduct optionalProduct = new OptionalProduct(name, monthlyFee);
        entityManager.persist(optionalProduct);
    }


    @Transactional
    public void saveServicePackage(String name, List<String> selectedOP, List<String> selectedVP, List<String> selectedS) {
        ServicePackage servicePackage = new ServicePackage(name);

        List<OptionalProduct> optionalProducts = new ArrayList<>();
        List<ValidityPeriod> validityPeriods = new ArrayList<>();
        List<TelcoService> services = new ArrayList<>();

        if (selectedOP != null) {
            for (String s : selectedOP)
                optionalProducts.add(entityManager.find(OptionalProduct.class, Long.parseLong(s)));
        }

        for (String s : selectedVP)
            validityPeriods.add(entityManager.find(ValidityPeriod.class, Long.parseLong(s)));

        for (String s : selectedS)
            services.add(entityManager.find(TelcoService.class, Long.parseLong(s)));

        servicePackage.getOptionalProducts().addAll(optionalProducts);
        servicePackage.getValidityPeriods().addAll(validityPeriods);
        servicePackage.getServices().addAll(services);

        entityManager.persist(servicePackage);
    }

}
