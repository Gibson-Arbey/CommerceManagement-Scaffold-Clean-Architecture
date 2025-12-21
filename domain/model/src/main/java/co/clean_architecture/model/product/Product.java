package co.clean_architecture.model.product;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Product {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Long categoryId;

    private String status;

    private LocalDate createdAt;

}
