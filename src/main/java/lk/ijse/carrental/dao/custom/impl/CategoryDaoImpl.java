package lk.ijse.carrental.dao.custom.impl;

import lk.ijse.carrental.dao.custom.CategoryDao;
import lk.ijse.carrental.entity.CategoryEntity;
import lk.ijse.carrental.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public void save(CategoryEntity entity) {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);

        transaction.commit();
    }

    @Override
    public CategoryEntity search(String id) {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CategoryEntity categoryEntity = session.get(CategoryEntity.class,id);

        transaction.commit();
        return categoryEntity;

    }

    @Override
    public void update(CategoryEntity entity) {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);

        transaction.commit();
    }

    @Override
    public void delete(CategoryEntity entity) {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entity);

        transaction.commit();

    }

    @Override
    public List<CategoryEntity> getAll() {
        Session session = SessionFactoryConfiguration.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM CategoryEntity";
        Query<CategoryEntity> queries = session.createQuery(hql, CategoryEntity.class);
        transaction.commit();
        return queries.list();
    }
}
