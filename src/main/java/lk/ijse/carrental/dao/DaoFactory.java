package lk.ijse.carrental.dao;

import lk.ijse.carrental.dao.custom.impl.CustomerDaoImpl;

public class DaoFactory {
    public static <T> T getDao(DaoType type){
        switch (type){
            case CUSTOMER:
                return (T) new CustomerDaoImpl();
            default:
                return null;
        }
    }
}
