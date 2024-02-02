package lk.ijse.carrental.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="car_category")
public class CategoryEntity {

    @Id
    @Column(name="categoryId")
    private String id;

    @Column(name="categoryName", nullable = false)
    private String name;

    @OneToMany(mappedBy = "categoryEntity", targetEntity = CarEntity.class)
    @ToString.Exclude
    private List<CarEntity> carEntities;

    public CategoryEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
