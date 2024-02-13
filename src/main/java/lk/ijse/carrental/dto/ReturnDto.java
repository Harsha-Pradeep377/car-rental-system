package lk.ijse.carrental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReturnDto {
    private String id;
    private String bookedId;
    private LocalDate returnedDate;
    private Integer overdueDays;
    private Double overdueAmount;
    private Double damageCost;
    private Double finalAmount;
}
