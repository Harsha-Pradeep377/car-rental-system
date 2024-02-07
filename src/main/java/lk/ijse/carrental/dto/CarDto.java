package lk.ijse.carrental.dto;

import lk.ijse.carrental.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarDto {
    private String id;
    private String brand;
    private String model;
    private String colour;
    private String vehicleNo;
    private Integer year;
    private Double price;
    private String catId;
    private Boolean isAvailability;

    public CarDto(String id, String brand, String model, String colour, String vehicleNo, Integer year, Double price, String catId) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.vehicleNo = vehicleNo;
        this.year = year;
        this.price = price;
        this.catId = catId;
    }
}
