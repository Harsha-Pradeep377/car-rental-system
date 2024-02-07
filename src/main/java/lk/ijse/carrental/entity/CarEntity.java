package lk.ijse.carrental.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


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

    @Column(nullable = false)
    private Boolean iaAvailable;

    @OneToMany(mappedBy = "carEntity", targetEntity = BookingEntity.class)
    @ToString.Exclude
    private List<BookingEntity> bookingEntities;

    public CarEntity(String id, String brand, String model, String colour, String vehicleNo, Integer year, Double price, CategoryEntity categoryEntity, Boolean iaAvailable) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.vehicleNo = vehicleNo;
        this.year = year;
        this.price = price;
        this.categoryEntity = categoryEntity;
        this.iaAvailable = iaAvailable;
    }
}
