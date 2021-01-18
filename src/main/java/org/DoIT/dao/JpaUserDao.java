package org.DoIT.dao;

import org.DoIT.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class JpaUserDao implements UserDao {

    @PersistenceContext(unitName = "emf")
    private EntityManager entityManager;

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getByEmail(String email) {
        TypedQuery<User> q = entityManager.createQuery("select u from User u where u.email = :email", User.class);
        q.setParameter("email", email);
        return q.getResultList().stream().findAny().orElse(null);
    }

    @Override
    @Transactional
    public void createUser(User user) {
        entityManager.persist(user);
    }


}
