package lk.ijse.carrental.dao.custom.impl;

import lk.ijse.carrental.dao.custom.UserDao;
import lk.ijse.carrental.entity.CustomerEntity;
import lk.ijse.carrental.entity.UserEntity;
import lk.ijse.carrental.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public void save(UserEntity entity) {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);

        transaction.commit();
    }

    @Override
    public UserEntity search(String id) {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        UserEntity userEntity = session.get(UserEntity.class,id);

        transaction.commit();
        return userEntity;
    }

    @Override
    public void update(UserEntity entity) {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);

        transaction.commit();
    }

    @Override
    public void delete(UserEntity entity) {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entity);

        transaction.commit();
    }

    @Override
    public List<UserEntity> getAll() {
        return null;
    }
}
