package lk.ijse.carrental.service.custom;

import lk.ijse.carrental.service.custom.impl.CategoryServiceImpl;
import lk.ijse.carrental.service.custom.impl.CustomerServiceImpl;

public class ServiceFactory {
    public static <T> T getService(ServiceType type){
        switch (type){
            case CUSTOMER:
                return (T) new CustomerServiceImpl();
            case CATEGORY:
                return (T) new CategoryServiceImpl();
            default:
                return null;
        }
    }
}
