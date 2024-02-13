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
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM ReturnEntity";
        List<ReturnEntity> entities = session.createQuery(hql, ReturnEntity.class).list();
        transaction.commit();
        session.close();
        return entities;

    }

    @Override
    public ReturnEntity search(String id) {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        ReturnEntity returnEntity = session.get(ReturnEntity.class,id);

        transaction.commit();

        return returnEntity;
    }
    @Override
    public void delete(ReturnEntity entity) {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entity);

        transaction.commit();
    }

    @Override
    public void update(ReturnEntity entity) {

    }



}
