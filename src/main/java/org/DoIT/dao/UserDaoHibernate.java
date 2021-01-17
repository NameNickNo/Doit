package org.DoIT.dao;

import org.DoIT.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoHibernate implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<User> getAll() {
        return currentSession().createQuery("from User ", User.class).list();
    }

    @Override
    public User getByEmail(String email) {
        Query<User> q = currentSession().createQuery("from User where email = :email", User.class);
        q.setParameter("email", email);
        return q.list().stream().findAny().orElse(null);
    }

    @Override
    public void createUser(User user) {
        currentSession().save(user);
    }
}
