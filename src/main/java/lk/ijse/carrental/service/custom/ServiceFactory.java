package lk.ijse.carrental.service.custom;

import lk.ijse.carrental.service.custom.impl.*;

public class ServiceFactory {
    public static <T> T getService(ServiceType type){
        switch (type){
            case CUSTOMER:
                return (T) new CustomerServiceImpl();
            case CATEGORY:
                return (T) new CategoryServiceImpl();
            case CAR:
                return (T) new CarServiceImpl();
            case BOOKING:
                return (T) new BookingServiceImpl();
            case RETURN:
                return (T) new ReturnServiceImpl();
            default:
                return null;
        }
    }
}
