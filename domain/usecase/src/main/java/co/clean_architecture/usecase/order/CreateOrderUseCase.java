package co.clean_architecture.usecase.order;

import co.clean_architecture.model.customer.exception.CustomerNotFoundException;
import co.clean_architecture.model.customer.gateways.CustomerRepository;
import co.clean_architecture.model.order.Order;
import co.clean_architecture.model.order.gateways.OrderRepository;
import co.clean_architecture.model.orderitem.OrderItem;
import co.clean_architecture.model.product.exception.ProductNotFoundException;
import co.clean_architecture.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public Order createOrder(Order order) {
        validateCustomerExists(order.getCustomerId());
        validateProductsExist(order.getOrderItems());
        List<OrderItem> items = order.getOrderItems();
        BigDecimal total = items.stream()
            .map(OrderItem::getSubTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order newOrder = Order.builder()
            .customerId(order.getCustomerId())
            .status(OrderStatus.CREATED.name())
            .createdAt(LocalDate.now())
            .totalAmount(total)
            .orderItems(order.getOrderItems())
            .build();

        return orderRepository.createOrderWithItems(newOrder);
    }

    private void validateCustomerExists(Long customerId) {
        if (customerRepository.getCustomerById(customerId) == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " does not exist.");
        }
    }

    private void validateProductsExist(List<OrderItem> items) {
        for (OrderItem item : items) {
            if (!productRepository.existsById(item.getProductId())) {
                throw new ProductNotFoundException("Product with ID " + item.getProductId() + " does not exist.");
            }
        }
    }
}
