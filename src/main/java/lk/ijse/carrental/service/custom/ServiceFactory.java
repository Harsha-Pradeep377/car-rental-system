package lk.ijse.carrental.service.custom;

import lk.ijse.carrental.service.custom.impl.CarServiceImpl;
import lk.ijse.carrental.service.custom.impl.CategoryServiceImpl;
import lk.ijse.carrental.service.custom.impl.CustomerServiceImpl;

public class ServiceFactory {
    public static <T> T getService(ServiceType type){
        switch (type){
            case CUSTOMER:
                return (T) new CustomerServiceImpl();
            case CATEGORY:
                return (T) new CategoryServiceImpl();
            case CAR:
                return (T) new CarServiceImpl();
            default:
                return null;
        }
    }
}
