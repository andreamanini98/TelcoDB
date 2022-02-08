package it.polimi.telcodb.services;

import it.polimi.telcodb.entities.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class UserService {

    @PersistenceContext
    EntityManager entityManager;


    @Transactional
    public void saveUser(String username, String email, String password) {
        User user = new User(username, email, password);
        entityManager.persist(user);
    }

}
