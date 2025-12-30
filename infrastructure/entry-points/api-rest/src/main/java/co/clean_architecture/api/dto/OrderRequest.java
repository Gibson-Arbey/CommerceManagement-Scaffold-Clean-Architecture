package co.clean_architecture.api.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequest {

    private Long customerId;
    private List<OrderItemRequest> orderItems;
}
