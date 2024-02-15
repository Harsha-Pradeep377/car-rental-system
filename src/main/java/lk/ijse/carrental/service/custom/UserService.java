package lk.ijse.carrental.service.custom;

import lk.ijse.carrental.dto.CustomerDto;
import lk.ijse.carrental.dto.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    UserDto search(String userId);

    void updateUser(UserDto userDto);


    void deleteUser(UserDto dto);
}
