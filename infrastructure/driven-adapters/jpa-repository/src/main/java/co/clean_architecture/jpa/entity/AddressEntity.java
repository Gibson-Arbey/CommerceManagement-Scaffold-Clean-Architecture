package co.clean_architecture.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {

    @Id
    @Column(name = "addr_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cust_id", nullable = false)
    private CustomerEntity customer;

    @Column(name = "addr_city", nullable = false)
    private String city;

    @Column(name = "addr_street", nullable = false)
    private String street;

    @Column(name = "addr_zipcode", nullable = false)
    private String zipCode;

    @Column(name = "addr_createdat", nullable = false)
    private LocalDate createdAt;
}
