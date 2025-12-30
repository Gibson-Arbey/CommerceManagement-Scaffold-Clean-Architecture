package co.clean_architecture.model.order.gateways;

import co.clean_architecture.model.order.Order;

import java.util.List;

public interface OrderRepository {

    Order createOrderWithItems(Order order);

    Order getOrderById(Long orderId);

    List<Order> getOrdersByCustomerId(Long customerId);

    void deleteOrderById(Long orderId);

    Boolean existsOrderById(Long orderId);

    void updateOrderStatus(Long orderId, String status);
}
