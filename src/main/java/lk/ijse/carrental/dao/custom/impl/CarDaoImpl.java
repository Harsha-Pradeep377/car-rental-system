package lk.ijse.carrental.dao.custom.impl;

import lk.ijse.carrental.dao.custom.CarDao;
import lk.ijse.carrental.entity.CarEntity;
import lk.ijse.carrental.entity.CategoryEntity;
import lk.ijse.carrental.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CarDaoImpl implements CarDao {
    @Override
    public void save(CarEntity entity) {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);

        transaction.commit();
    }

    @Override
    public CarEntity search(String id) {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CarEntity carEntity = session.get(CarEntity.class,id);

        transaction.commit();

        return carEntity;
    }

    @Override
    public void update(CarEntity entity) {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);

        transaction.commit();
    }

    @Override
    public void delete(CarEntity entity) {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entity);

        transaction.commit();
    }

    @Override
    public List<CarEntity> getAll() {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM CarEntity";
        List<CarEntity> entities = session.createQuery(hql, CarEntity.class).list();
        transaction.commit();
        session.close();
        return entities;

    }
}
