package lk.ijse.carrental.dao.custom.impl;

import lk.ijse.carrental.dao.custom.CustomerDao;
import lk.ijse.carrental.entity.CustomerEntity;
import lk.ijse.carrental.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public void save(CustomerEntity entity) {

        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);

        transaction.commit();

    }
}
