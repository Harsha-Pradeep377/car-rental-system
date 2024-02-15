package lk.ijse.carrental.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user")
@Entity
public class UserEntity {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(name = "user_name",nullable = false)
    private String userName;

    @Column(name = "passward",nullable = false)
    private String pass;

    @Column
    private String email;

    @Column(nullable = false)
    private String mobile;
}
