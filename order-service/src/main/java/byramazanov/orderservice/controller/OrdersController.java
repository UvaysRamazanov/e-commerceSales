package byramazanov.orderservice.controller;

import byramazanov.core.dto.Order;
import byramazanov.orderservice.dto.CreateOrderRequest;
import byramazanov.orderservice.dto.CreateOrderResponse;
import byramazanov.orderservice.dto.OrderHistoryResponse;
import byramazanov.orderservice.service.OrderHistoryService;
import byramazanov.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrderService orderService;
    private final OrderHistoryService orderHistoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CreateOrderResponse placeOrder(@RequestBody @Valid CreateOrderRequest request) {
        Order order = Order.builder()
                .customerId(request.getCustomerId())
                .productId(request.getProductId())
                .productQuantity(request.getProductQuantity())
                .build();

        Order createdOrder = orderService.placeOrder(order);

        return CreateOrderResponse.builder()
                .orderId(createdOrder.getOrderId())
                .customerId(createdOrder.getCustomerId())
                .productId(createdOrder.getProductId())
                .productQuantity(createdOrder.getProductQuantity())
                .status(createdOrder.getStatus())
                .build();
    }

    @GetMapping("/{orderId}/history")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderHistoryResponse> getOrderHistory(@PathVariable UUID orderId) {
        return orderHistoryService.findByOrderId(orderId).stream().map(orderHistory -> {
            OrderHistoryResponse orderHistoryResponse = new OrderHistoryResponse();
            BeanUtils.copyProperties(orderHistory, orderHistoryResponse);
            // todo Заменить copyProperties на MapStruct (на будущее)
            return orderHistoryResponse;
        }).toList();
    }
}
