package it.polimi.telcodb.security;

import it.polimi.telcodb.entities.Employee;
import it.polimi.telcodb.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = entityManager.find(User.class, username);
        if (user == null) {
            Employee employee = entityManager.find(Employee.class, username);
            if (employee == null)
                throw new UsernameNotFoundException("User not found");
            return new EmployeePrincipal(employee);
        }
        return new UserPrincipal(user);
    }

}
