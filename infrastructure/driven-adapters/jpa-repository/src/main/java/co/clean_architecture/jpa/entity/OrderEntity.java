package co.clean_architecture.jpa.entity;

import co.clean_architecture.model.customer.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @Column(name = "orde_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cust_id", nullable = false)
    private CustomerEntity customer;

    @Column(name = "orde_status", nullable = false)
    private String status;

    @Column(name = "orde_totalamount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "orde_createdat", nullable = false)
    private LocalDate createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> items;

    public OrderEntity(Long id) {
        this.id = id;
    }
}
