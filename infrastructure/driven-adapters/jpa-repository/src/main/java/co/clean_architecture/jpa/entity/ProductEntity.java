package co.clean_architecture.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "products")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @Column(name = "prod_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prod_name", nullable = false)
    private String name;

    @Column(name = "prod_description", nullable = false)
    private String description;

    @Column(name = "prod_price", nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "cate_id", nullable = false)
    private CategoryEntity category;

    @Column(name = "prod_status", nullable = false)
    private String status;

    @Column(name = "prodr_createdat", nullable = false)
    private LocalDate createdAt;

    public ProductEntity(Long id) {
        this.id = id;
    }
}
