package byramazanov.orderservice.service;

import byramazanov.core.types.OrderStatus;
import byramazanov.orderservice.dto.OrderHistory;
import byramazanov.orderservice.jpa.entity.OrderHistoryEntity;
import byramazanov.orderservice.jpa.repository.OrderHistoryRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderHistoryServiceImpl implements OrderHistoryService {

    private final OrderHistoryRepository orderHistoryRepository;

    @Override
    public void add(
            @NotNull UUID orderId,
            @NotNull OrderStatus orderStatus
                                    ) {
        OrderHistoryEntity entity = OrderHistoryEntity.builder()
                .orderId(orderId)
                .status(orderStatus)
                .createdAt(Instant.now())
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
