package co.clean_architecture.jpa.adapter;

import co.clean_architecture.jpa.entity.CustomerEntity;
import co.clean_architecture.jpa.entity.OrderEntity;
import co.clean_architecture.jpa.entity.OrderItemEntity;
import co.clean_architecture.jpa.entity.ProductEntity;
import co.clean_architecture.jpa.repository.OrderJpaRepository;
import co.clean_architecture.model.order.Order;
import co.clean_architecture.model.order.gateways.OrderRepository;
import co.clean_architecture.model.orderitem.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    @Transactional
    public Order createOrderWithItems(Order order) {
        return mapOrderEntityToOrder(
                orderJpaRepository.save(
                        mapOrderToOrderEntity(order)
                )
        );
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderJpaRepository.findById(orderId)
                .map(this::mapOrderEntityToOrder)
                .orElse(null);
    }

    @Override
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderJpaRepository.findByCustomerId(customerId)
                .stream()
                .map(this::mapOrderEntityToOrder)
                .toList();
    }

    @Override
    @Transactional
    public void deleteOrderById(Long orderId) {
        orderJpaRepository.deleteById(orderId);
    }

    @Override
    public Boolean existsOrderById(Long orderId) {
        return orderJpaRepository.existsById(orderId);
    }

    @Override
    @Transactional
    public void updateOrderStatus(Long orderId, String status) {
        orderJpaRepository.updateOrderStatus(orderId, status);
    }

    private Order mapOrderEntityToOrder(OrderEntity entity) {
        return Order.builder()
                .id(entity.getId())
                .customerId(entity.getCustomer().getId())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .totalAmount(entity.getTotalAmount())
                .orderItems(
                        entity.getItems().stream()
                                .map(i -> OrderItem.builder()
                                        .id(i.getId())
                                        .orderId(i.getOrder().getId())
                                        .productId(i.getProduct().getId())
                                        .quantity(i.getQuantity())
                                        .unitPrice(i.getUnitPrice())
                                        .createdAt(i.getCreatedAt())
                                        .build())
                                .toList()
                )
                .build();
    }


    private OrderEntity mapOrderToOrderEntity(Order order) {

        OrderEntity orderEntity = OrderEntity.builder()
                .id(order.getId())
                .customer(new CustomerEntity(order.getCustomerId()))
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .build();

        List<OrderItemEntity> itemEntities = order.getOrderItems().stream()
                .map(item -> OrderItemEntity.builder()
                        .order(orderEntity)
                        .product(new ProductEntity(item.getProductId()))
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .subTotal(item.getSubTotal())
                        .createdAt(item.getCreatedAt())
                        .build()
                )
                .toList();

        orderEntity.setItems(itemEntities);

        return orderEntity;
    }

}
