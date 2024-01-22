package lk.ijse.carrental.service.custom;

import lk.ijse.carrental.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
     void saveCustomer(CustomerDto customerDto);

    CustomerDto search(String custId);

    List<CustomerDto> getAll();

    void updateCustomer(CustomerDto custDto);


    void deleteCustomer(CustomerDto dto);
}
