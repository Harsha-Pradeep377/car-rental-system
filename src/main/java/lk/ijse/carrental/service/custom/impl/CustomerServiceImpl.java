package lk.ijse.carrental.service.custom.impl;

import lk.ijse.carrental.dao.DaoFactory;
import lk.ijse.carrental.dao.DaoType;
import lk.ijse.carrental.dao.custom.CustomerDao;
import lk.ijse.carrental.dto.CustomerDto;
import lk.ijse.carrental.entity.CustomerEntity;
import lk.ijse.carrental.service.custom.CustomerService;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao = DaoFactory.getDao(DaoType.CUSTOMER);
    @Override
    public void saveCustomer(CustomerDto dto) {
        var customerEntity = new CustomerEntity(dto.getId(),dto.getName(),dto.getNic(),dto.getAddress(),dto.getContact());

        customerDao.save(customerEntity);


    }
}
