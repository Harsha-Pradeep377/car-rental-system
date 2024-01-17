package lk.ijse.carrental.dao.custom;

public interface CrudDao<T,Id> {
    void save(T entity);
}
