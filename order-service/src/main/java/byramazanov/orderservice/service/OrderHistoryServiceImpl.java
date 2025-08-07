package byramazanov.orderservice.service;

import byramazanov.core.types.OrderStatus;
import byramazanov.orderservice.dto.OrderHistory;
import byramazanov.orderservice.jpa.entity.OrderHistoryEntity;
import byramazanov.orderservice.jpa.repository.OrderHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderHistoryServiceImpl implements OrderHistoryService {
    private final OrderHistoryRepository orderHistoryRepository;

    @Override
    public void add(UUID orderId, OrderStatus orderStatus) {
        OrderHistoryEntity entity = OrderHistoryEntity.builder()
                .orderId(orderId)
                .status(orderStatus)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();

        orderHistoryRepository.save(entity);
    }

    @Override
    public List<OrderHistory> findByOrderId(UUID orderId) {
        return orderHistoryRepository.findByOrderId(orderId).stream()
                .map(entity -> OrderHistory.builder()
                        .id(entity.getId())
                        .orderId(entity.getOrderId())
                        .status(entity.getStatus())
                        .createdAt(entity.getCreatedAt())
                        .build())
                .toList();
    }
}
