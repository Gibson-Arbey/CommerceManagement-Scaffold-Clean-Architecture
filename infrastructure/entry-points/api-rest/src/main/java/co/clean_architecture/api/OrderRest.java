package co.clean_architecture.api;

import co.clean_architecture.api.dto.OrderRequest;
import co.clean_architecture.model.order.Order;
import co.clean_architecture.model.orderitem.OrderItem;
import co.clean_architecture.usecase.order.CreateOrderUseCase;
import co.clean_architecture.usecase.order.OrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
public class OrderRest {

    private final CreateOrderUseCase createOrderUseCase;
    private final OrderUseCase orderUseCase;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request) {

        Order order = Order.builder()
            .customerId(request.getCustomerId())
            .orderItems(
                request.getOrderItems().stream()
                    .map(i -> OrderItem.builder()
                        .productId(i.getProductId())
                        .quantity(i.getQuantity())
                        .unitPrice(i.getUnitPrice())
                        .build())
                    .toList()
            )
            .build();

        return ResponseEntity.ok(createOrderUseCase.createOrder(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderUseCase.getOrderById(id));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Order>> getOrderByCustomerId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderUseCase.getOrdersByCustomerId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
        orderUseCase.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable("id") Long id, @RequestParam("status") String status) {
        orderUseCase.updateOrderStatus(id, status);
        return ResponseEntity.noContent().build();
    }

}
