package lk.ijse.carrental.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
