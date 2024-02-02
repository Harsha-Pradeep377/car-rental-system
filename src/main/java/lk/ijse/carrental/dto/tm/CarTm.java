package lk.ijse.carrental.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarTm {
    private String id;
    private String brand;
    private String model;
    private String colour;
    private String vehicleNo;
    private Integer year;
    private Double price;
    private String category;
}
