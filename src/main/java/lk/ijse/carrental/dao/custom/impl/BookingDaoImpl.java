package lk.ijse.carrental.dao.custom.impl;

import lk.ijse.carrental.dao.custom.BookingDao;
import lk.ijse.carrental.entity.BookingEntity;
import lk.ijse.carrental.entity.CarEntity;
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
    public BookingEntity search(String id) {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        BookingEntity bookingEntity = session.get(BookingEntity.class,id);

        transaction.commit();

        return bookingEntity;
    }

    @Override
    public void update(BookingEntity entity) {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);

        transaction.commit();
    }

    @Override
    public void delete(BookingEntity entity) {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entity);

        transaction.commit();

    }

    @Override
    public List<BookingEntity> getAll() {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM BookingEntity";
        List<BookingEntity> entities = session.createQuery(hql, BookingEntity.class).list();
        transaction.commit();
        session.close();
        return entities;

    }
}
