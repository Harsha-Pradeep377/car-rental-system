package lk.ijse.carrental.dao.custom.impl;

import lk.ijse.carrental.dao.custom.BookingDao;
import lk.ijse.carrental.entity.BookingEntity;
import lk.ijse.carrental.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BookingDaoImpl implements BookingDao {

    @Override
    public void save(BookingEntity entity) {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);

        transaction.commit();
    }

    @Override
    public BookingEntity search(String s) {
        return null;
    }

    @Override
    public void update(BookingEntity entity) {

    }

    @Override
    public void delete(BookingEntity entity) {

    }

    @Override
    public List<BookingEntity> getAll() {
        return null;
    }
}
