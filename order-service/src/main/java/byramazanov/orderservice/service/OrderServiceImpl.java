package byramazanov.orderservice.service;

import byramazanov.core.dto.Order;
import byramazanov.core.dto.event.OrderCreatedEvent;
import byramazanov.core.types.OrderStatus;
import byramazanov.orderservice.jpa.entity.OrderEntity;
import byramazanov.orderservice.jpa.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String orderEventsTopicName;

    public OrderServiceImpl(
            KafkaTemplate<String, Object> kafkaTemplate,
            OrderRepository orderRepository,
            @Value("${orders.events.topic.name}") String orderEventsTopicName
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderRepository = orderRepository;
        this.orderEventsTopicName = orderEventsTopicName;
    }

    @Override
    public Order placeOrder(Order order) {
        OrderEntity entity = OrderEntity.builder()
                .customerId(order.getCustomerId())
                .productId(order.getProductId())
                .productQuantity(order.getProductQuantity())
                .status(OrderStatus.CREATED)
                .build();
        orderRepository.save(entity);

        OrderCreatedEvent placedOrder = new OrderCreatedEvent(
                entity.getId(),
                entity.getCustomerId(),
                order.getProductId(),
                order.getProductQuantity()
        );

        kafkaTemplate.send(orderEventsTopicName, placedOrder);

        return Order.builder()
                .orderId(entity.getId())
                .customerId(entity.getCustomerId())
                .productId(entity.getProductId())
                .productQuantity(entity.getProductQuantity())
                .status(entity.getStatus())
                .build();
    }

}
