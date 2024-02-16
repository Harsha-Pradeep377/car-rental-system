package lk.ijse.carrental.service.custom.impl;

import lk.ijse.carrental.dao.DaoFactory;
import lk.ijse.carrental.dao.DaoType;
import lk.ijse.carrental.dao.custom.CustomerDao;
import lk.ijse.carrental.dao.custom.UserDao;
import lk.ijse.carrental.dto.CustomerDto;
import lk.ijse.carrental.dto.UserDto;
import lk.ijse.carrental.entity.CustomerEntity;
import lk.ijse.carrental.entity.UserEntity;
import lk.ijse.carrental.service.custom.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = DaoFactory.getDao(DaoType.USER);
    @Override
    public void saveUser(UserDto userDto) {
        var userEntity = new UserEntity(
                userDto.getId(),
                userDto.getName(),
                userDto.getUserName(),
                userDto.getPass(),
                userDto.getEmail(),
                userDto.getMobile());

        userDao.save(userEntity);
    }

    @Override
    public UserDto search(String userId) {
        UserEntity entity = userDao.search(userId);

        return new UserDto(entity.getId(), entity.getName(), entity.getUserName(), entity.getPass(), entity.getEmail(),entity.getMobile());
    }

    @Override
    public void updateUser(UserDto userDto) {
        var userEntity = new UserEntity(userDto.getId(),userDto.getName(),userDto.getUserName(),userDto.getPass(),userDto.getEmail(), userDto.getMobile());

        userDao.update(userEntity);
    }

    @Override
    public void deleteUser(UserDto dto) {
        var userEntity = new UserEntity(dto.getId(),dto.getName(),dto.getUserName(),dto.getPass(),dto.getEmail(), dto.getMobile());
        userDao.delete(userEntity);
    }

    @Override
    public UserDto getUser(String userName) {
        UserEntity entity = userDao.getUser(userName);
        if(entity != null){
            return new UserDto(entity.getId(), entity.getName(), entity.getUserName(), entity.getPass(), entity.getEmail(),entity.getMobile());
        }else {
            return null;
        }
    }
}
