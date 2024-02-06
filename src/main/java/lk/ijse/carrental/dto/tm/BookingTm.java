package lk.ijse.carrental.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingTm {
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
