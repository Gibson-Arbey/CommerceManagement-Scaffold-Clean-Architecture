package co.clean_architecture.api.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class OrderItemRequest {

    private Long productId;
    private Integer quantity;
    private BigDecimal unitPrice;
}
