package lk.ijse.carrental.dao.custom.impl;

import lk.ijse.carrental.dao.custom.ReturnDao;
import lk.ijse.carrental.entity.BookingEntity;
import lk.ijse.carrental.entity.ReturnEntity;
import lk.ijse.carrental.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReturnDaoImpl implements ReturnDao {
    @Override
    public void save(ReturnEntity entity) {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);

        transaction.commit();
    }

    @Override
    public List<ReturnEntity> getAll() {
        return null;
    }

    @Override
    public ReturnEntity search(String s) {
        return null;
    }

    @Override
    public void update(ReturnEntity entity) {

    }

    @Override
    public void delete(ReturnEntity entity) {

    }

}
