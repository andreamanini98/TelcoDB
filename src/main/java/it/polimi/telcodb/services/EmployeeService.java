package it.polimi.telcodb.services;

import it.polimi.telcodb.entities.Employee;
import it.polimi.telcodb.entities.OptionalProduct;
import it.polimi.telcodb.entities.TelcoService;
import it.polimi.telcodb.entities.ValidityPeriod;
import it.polimi.telcodb.enums.ServiceType;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;

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
        service.setServiceType(ServiceType.FIXED_PHONE);
        entityManager.persist(service);
    }


    @Transactional
    public void saveMobilePhoneService(int nMinutes, int nSMSs, BigDecimal extraMinFee, BigDecimal extraGigaFee) {
        TelcoService service = new TelcoService();
        service.setServiceType(ServiceType.MOBILE_PHONE);
        service.setNumberOfMinutes(nMinutes);
        service.setNumberOfSMSs(nSMSs);
        service.setExtraMinutesFee(extraMinFee);
        service.setExtraGigabytesFee(extraGigaFee);
        entityManager.persist(service);
    }


    @Transactional
    public void saveFixedMobileInternetService(ServiceType serviceType, int nGiga, BigDecimal extraGigaFee) {
        TelcoService service = new TelcoService();
        if (serviceType.equals(ServiceType.FIXED_INTERNET))
            service.setServiceType(ServiceType.FIXED_INTERNET);
        else service.setServiceType(ServiceType.MOBILE_INTERNET);
        service.setNumberOfGigabytes(nGiga);
        service.setExtraGigabytesFee(extraGigaFee);
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

}
