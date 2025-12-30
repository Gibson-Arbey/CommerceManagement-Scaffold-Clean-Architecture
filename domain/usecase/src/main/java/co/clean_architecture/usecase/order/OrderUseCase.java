package co.clean_architecture.usecase.order;

import co.clean_architecture.model.order.Order;
import co.clean_architecture.model.order.exception.OrderNotFoundException;
import co.clean_architecture.model.order.gateways.OrderRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrderUseCase {

    private final OrderRepository orderRepository;

    public Order getOrderById(Long orderId) {
        Order order = orderRepository.getOrderById(orderId);
        if (order == null) {
            throw new OrderNotFoundException("Order not found with id: " + orderId);
        }
        return order;
    }

    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.getOrdersByCustomerId(customerId);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteOrderById(orderId);
    }
}
