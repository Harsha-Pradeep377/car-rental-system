package lk.ijse.carrental.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReturnTm {
    private String id;
    private String bookedId;
    private LocalDate returnedDate;
    private Integer overdueDays;
    private Double overdueAmount;
    private Double damageCost;
    private Double finalAmount;
}
