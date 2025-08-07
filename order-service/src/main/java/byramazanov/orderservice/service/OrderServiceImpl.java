package byramazanov.orderservice.service;

import byramazanov.core.dto.Order;
import byramazanov.core.types.OrderStatus;
import byramazanov.orderservice.jpa.entity.OrderEntity;
import byramazanov.orderservice.jpa.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Order placeOrder(Order order) {
        OrderEntity entity = OrderEntity.builder()
                .customerId(order.getCustomerId())
                .productId(order.getProductId())
                .productQuantity(order.getProductQuantity())
                .status(OrderStatus.CREATED)
                .build();

        orderRepository.save(entity);

        return Order.builder()
                .orderId(entity.getId())
                .customerId(entity.getCustomerId())
                .productId(entity.getProductId())
                .productQuantity(entity.getProductQuantity())
                .status(entity.getStatus())
                .build();
    }

}
