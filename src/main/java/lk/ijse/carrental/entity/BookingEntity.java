package lk.ijse.carrental.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "bookingDetails")
public class BookingEntity {
    @Id
    @Column
    private String id;

    @ManyToOne
    @JoinColumn(name = "custId", nullable = false)
    private CustomerEntity customerEntity;

    @ManyToOne
    @JoinColumn(name = "carId", nullable = false)
    private CarEntity carEntity;

    @Column(nullable = false)
    private LocalDate bookDate;

    @Column(nullable = false)
    private LocalDate returnDate;

    @Column(nullable = false)
    private Double rate;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    private Double advance;

    @Column(nullable = false)
    private Double balance;
}
