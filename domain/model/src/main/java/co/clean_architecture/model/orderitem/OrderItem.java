package co.clean_architecture.model.orderitem;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrderItem {

    private Long id;
    private Long productId;
    private Long orderId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subTotal;

    private LocalDate createdAt;

    public BigDecimal getSubTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
