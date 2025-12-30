package co.clean_architecture.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "orderitems")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntity {

    @Id
    @Column(name = "orit_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orde_id")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "prod_id", nullable = false)
    private ProductEntity product;

    @Column(name = "orit_quantity", nullable = false)
    private Integer quantity;

    @Column(name = "orit_unitprice", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "orit_subtotal", nullable = false)
    private BigDecimal subTotal;

    @Column(name = "orit_createdat", nullable = false)
    private LocalDate createdAt;

    @PrePersist
    void prePersist() {
        this.createdAt = LocalDate.now();
    }

}
