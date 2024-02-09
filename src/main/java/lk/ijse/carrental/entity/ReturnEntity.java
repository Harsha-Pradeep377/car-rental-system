package lk.ijse.carrental.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "return_Details")
public class ReturnEntity {
    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "bookedId",nullable = false)
    private BookingEntity bookedEntity;

    @Column(nullable = false)
    private LocalDate returnedDate;

    @Column(nullable = false)
    private Integer overdueDays;

    @Column(nullable = false)
    private Double overdueAmount;

    @Column(nullable = false)
    private Double damageCost;

    @Column(nullable = false)
    private Double finalAmount;
}
