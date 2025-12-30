package co.clean_architecture.usecase.order;

import co.clean_architecture.model.order.Order;
import co.clean_architecture.model.order.exception.OrderNotFoundException;
import co.clean_architecture.model.order.gateways.OrderRepository;
import co.clean_architecture.usecase.order.exception.StatusOrderIsNotValidException;
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

    public void updateOrderStatus(Long orderId, String status) {
        validateOrderExists(orderId);
        validateStatus(status);
        orderRepository.updateOrderStatus(orderId, status);
    }

    private void validateOrderExists(Long orderId) {
        if (Boolean.FALSE.equals(orderRepository.existsOrderById(orderId))) {
            throw new OrderNotFoundException("Order not found with id: " + orderId);
        }
    }

    private void validateStatus(String status) {
        try {
            StatusOrderEnum.valueOf(status);
        } catch (IllegalArgumentException | NullPointerException ex) {
            throw new StatusOrderIsNotValidException(
                    "The status '" + status + "' is not valid. Allowed values: " + List.of(StatusOrderEnum.values())
            );
        }
    }
}
