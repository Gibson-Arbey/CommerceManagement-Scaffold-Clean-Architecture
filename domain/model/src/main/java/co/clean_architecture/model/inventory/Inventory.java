package co.clean_architecture.model.inventory;
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
public class Inventory {

    private Long id;

    private Long productId;

    private Integer quantity;

    private Integer minimumQuantity;

    private LocalDate createdAt;
}
