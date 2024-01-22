package lk.ijse.carrental.dao.custom.impl;

import lk.ijse.carrental.dao.custom.CustomerDao;
import lk.ijse.carrental.entity.CustomerEntity;
import lk.ijse.carrental.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    }

    @Override
    public void delete(CustomerEntity entity) {

    }

    @Override
    public List<CustomerEntity> getAll() {
        return null;
    }
}
