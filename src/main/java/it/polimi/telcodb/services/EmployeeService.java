package it.polimi.telcodb.services;

import it.polimi.telcodb.entities.Employee;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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

}
