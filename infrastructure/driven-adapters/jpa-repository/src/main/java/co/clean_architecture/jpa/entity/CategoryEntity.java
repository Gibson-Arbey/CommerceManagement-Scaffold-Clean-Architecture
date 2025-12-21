package co.clean_architecture.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "categories")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {

    @Id
    @Column(name = "cate_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cate_name", nullable = false)
    private String name;

    @Column(name = "cate_description", nullable = false)
    private String description;

    @Column(name = "cater_createdat", nullable = false)
    private LocalDate createdAt;

    public CategoryEntity(Long id) {
        this.id = id;
    }
}
