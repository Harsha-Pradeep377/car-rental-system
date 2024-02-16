package lk.ijse.carrental.dao.custom;

import java.util.List;

public interface CrudDao<T,Id> {
    void save(T entity);

    T search(Id id);

    void update(T entity);

    void delete(T entity);

    List<T> getAll();

}
