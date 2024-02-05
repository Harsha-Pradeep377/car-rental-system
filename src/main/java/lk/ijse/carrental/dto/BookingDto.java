package lk.ijse.carrental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingDto {
    private String id;
    private String custId;
    private String carId;
    private LocalDate bookDate;
    private LocalDate returnDate;
    private Double rate;
    private Double total;
    private Double advance;
    private Double balance;
}
