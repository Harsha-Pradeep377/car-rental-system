package lk.ijse.carrental.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="car")
public class CarEntity {
    @Id
    @Column
    private String id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String colour;

    @Column(nullable = false)
    private String vehicleNo;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private CategoryEntity categoryEntity;
}
