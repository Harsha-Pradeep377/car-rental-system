package lk.ijse.carrental.dto.tm;

import java.time.LocalDate;

public class ReturnTm {
    private String id;
    private String bookedId;
    private LocalDate returnedDate;
    private Integer overdueDays;
    private Double overdueAmount;
    private Double damageCost;
    private Double finalAmount;
}
