package lk.ijse.carrental.service.custom.impl;

import lk.ijse.carrental.dao.DaoFactory;
import lk.ijse.carrental.dao.DaoType;
import lk.ijse.carrental.dao.custom.CarDao;
import lk.ijse.carrental.dao.custom.CategoryDao;
import lk.ijse.carrental.dto.CarDto;
import lk.ijse.carrental.entity.CarEntity;
import lk.ijse.carrental.entity.CategoryEntity;
import lk.ijse.carrental.service.custom.CarService;

import java.util.ArrayList;
import java.util.List;

public class CarServiceImpl implements CarService{

    private CarDao carDao = DaoFactory.getDao(DaoType.CAR);
    private CategoryDao categoryDao = DaoFactory.getDao(DaoType.CATEGORY);

    @Override
  public void saveCar(CarDto carDto) {
        CategoryEntity categoryEntity = categoryDao.search(carDto.getCatId());

         var carEntity = new CarEntity(
                carDto.getId(),
                carDto.getBrand(),
                carDto.getModel(),
                carDto.getColour(),
                carDto.getVehicleNo(),
                carDto.getYear(),
                carDto.getPrice(),
                categoryEntity,
                 carDto.getIsAvailability());

       carDao.save(carEntity);

    }

    @Override
    public CarDto search(String carId) {
        CarEntity carEntity = carDao.search(carId);
        CarDto carDto = new CarDto(
                carEntity.getId(),
                carEntity.getBrand(),
                carEntity.getModel(),
                carEntity.getColour(),
                carEntity.getVehicleNo(),
                carEntity.getYear(),
                carEntity.getPrice(),
                carEntity.getCategoryEntity().getId(),
                carEntity.getIsAvailability());
        return carDto;
    }

    @Override
    public List<CarDto> getAll() {
    List<CarDto> carDtos = new ArrayList<>();
    List<CarEntity> carEntities = carDao.getAll();
        for (CarEntity car : carEntities) {
            CarDto carDto = new CarDto(car.getId(), car.getBrand(), car.getModel(), car.getColour(), car.getVehicleNo(), car.getYear(), car.getPrice(), car.getCategoryEntity().getId(), car.getIsAvailability());
            carDtos.add(carDto);
        }
        return carDtos;
    }

    @Override
    public void updateCar(CarDto carDto) {
        CategoryEntity categoryEntity = categoryDao.search(carDto.getCatId());

        var carEntity = new CarEntity(
                carDto.getId(),
                carDto.getBrand(),
                carDto.getModel(),
                carDto.getColour(),
                carDto.getVehicleNo(),
                carDto.getYear(),
                carDto.getPrice(),
                categoryEntity,
                carDto.getIsAvailability());

        carDao.update(carEntity);

    }

    @Override
    public void deleteCar(CarDto dto) {
        CategoryEntity category = categoryDao.search(dto.getCatId());
        var carEntity = new CarEntity(dto.getId(), dto.getBrand(), dto.getModel(), dto.getColour(), dto.getVehicleNo(), dto.getYear(), dto.getPrice(),category,dto.getIsAvailability());
        carDao.delete(carEntity);
    }

    @Override
    public void updateIsAvailability(String carId, boolean availability) {
        CarEntity carEntity = carDao.search(carId);
        carEntity.setIsAvailability(availability);
        carDao.update(carEntity);
    }
}
