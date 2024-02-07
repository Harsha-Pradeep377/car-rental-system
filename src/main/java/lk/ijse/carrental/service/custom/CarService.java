package lk.ijse.carrental.service.custom;

import lk.ijse.carrental.dto.CarDto;
import java.util.List;

public interface CarService {
    void saveCar(CarDto carDto);

    CarDto search(String carId);

    List<CarDto> getAll();

    void updateCar(CarDto carDto);

    void deleteCar(CarDto dto);

    void updateIsAvailability(String carId, boolean availability);
}
