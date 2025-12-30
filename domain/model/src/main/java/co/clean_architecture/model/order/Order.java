package co.clean_architecture.model.order;
import co.clean_architecture.model.orderitem.OrderItem;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Order {

    private Long id;
    private Long customerId;
    private BigDecimal totalAmount;
    private LocalDate createdAt;
    private String status;

    private List<OrderItem> orderItems;
}
