package lk.ijse.carrental.dao;

import lk.ijse.carrental.dao.custom.impl.CarDaoImpl;
import lk.ijse.carrental.dao.custom.impl.CategoryDaoImpl;
import lk.ijse.carrental.dao.custom.impl.CustomerDaoImpl;

public class DaoFactory {
    public static <T> T getDao(DaoType type){
        switch (type){
            case CUSTOMER:
                return (T) new CustomerDaoImpl();
            case CATEGORY:
                return (T) new CategoryDaoImpl();
            case CAR:
                return (T) new CarDaoImpl();
            default:
                return null;
        }
    }
}
