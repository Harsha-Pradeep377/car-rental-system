package lk.ijse.carrental.dto;

import java.time.LocalDate;

public class ReturnDto {
    private String id;
    private String bookedId;
    private LocalDate returnedDate;
    private Integer overdueDays;
    private Double overdueAmount;
    private Double damageCost;
    private Double finalAmount;
}
