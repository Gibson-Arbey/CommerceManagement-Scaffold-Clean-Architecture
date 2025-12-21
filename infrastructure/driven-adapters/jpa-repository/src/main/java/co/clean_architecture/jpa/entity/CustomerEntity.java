package co.clean_architecture.jpa.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "customers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {

    @Id
    @Column(name = "cust_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cust_name", nullable = false)
    private String name;

    @Column(name = "cust_lastname", nullable = false)
    private String lastName;

    @Column(name = "name_email", unique = true, nullable = false)
    private String email;

    @Column(name = "cust_phone", unique = true, nullable = false)
    private String phone;

    @Column(name = "cust_status", nullable = false)
    private String status;

    @Column(name = "cust_createdat", nullable = false)
    private LocalDate createdAt;

    public CustomerEntity(Long id) {
        this.id = id;
    }

}
