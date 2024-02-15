package lk.ijse.carrental.dao;

import lk.ijse.carrental.dao.custom.impl.*;

public class DaoFactory {
    public static <T> T getDao(DaoType type){
        switch (type){
            case CUSTOMER:
                return (T) new CustomerDaoImpl();
            case CATEGORY:
                return (T) new CategoryDaoImpl();
            case CAR:
                return (T) new CarDaoImpl();
            case BOOKING:
                return (T) new BookingDaoImpl();
            case RETURN:
                return (T) new ReturnDaoImpl();
            case USER:
                return (T) new UserDaoImpl();
            default:
                return null;
        }
    }
}
