package lk.ijse.carrental.dao.custom.impl;

import lk.ijse.carrental.dao.custom.CustomerDao;
import lk.ijse.carrental.entity.CustomerEntity;
import lk.ijse.carrental.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public void save(CustomerEntity entity) {

        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);

        transaction.commit();

    }

    @Override
    public CustomerEntity search(String id) {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CustomerEntity customerEntity = session.get(CustomerEntity.class,id);

        transaction.commit();
        return customerEntity;
    }

    @Override
    public void update(CustomerEntity entity) {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);

        transaction.commit();

    }

    @Override
    public void delete(CustomerEntity entity) {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entity);

        transaction.commit();
    }


    @Override
    public List<CustomerEntity> getAll() {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM CustomerEntity";
        List<CustomerEntity> entities = session.createQuery(hql, CustomerEntity.class).list();
        transaction.commit();
        session.close();
        return entities;
    }
}
