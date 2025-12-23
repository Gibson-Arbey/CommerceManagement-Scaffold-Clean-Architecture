package co.clean_architecture.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "inventories")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEntity {

    @Id
    @Column(name = "inve_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "prod_id", nullable = false)
    private ProductEntity product;

    @Column(name = "inve_quantity", nullable = false)
    private Integer quantity;

    @Column(name = "inve_minimumquantity", nullable = false)
    private Integer minimumQuantity;

    private LocalDate createdAt;
}
