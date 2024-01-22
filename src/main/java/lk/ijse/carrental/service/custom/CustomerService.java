package lk.ijse.carrental.service.custom;

import lk.ijse.carrental.dto.CustomerDto;

public interface CustomerService {
     void saveCustomer(CustomerDto customerDto);

    CustomerDto search(String custId);
}
