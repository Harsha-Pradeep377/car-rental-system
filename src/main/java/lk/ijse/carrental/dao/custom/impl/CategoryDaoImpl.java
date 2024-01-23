package lk.ijse.carrental.dao.custom.impl;

import lk.ijse.carrental.dao.custom.CategoryDao;
import lk.ijse.carrental.entity.CategoryEntity;
import lk.ijse.carrental.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
    public CategoryEntity search(String s) {
        return null;
    }

    @Override
    public void update(CategoryEntity entity) {

    }

    @Override
    public void delete(CategoryEntity entity) {

    }

    @Override
    public List<CategoryEntity> getAll() {
        return null;
    }
}
