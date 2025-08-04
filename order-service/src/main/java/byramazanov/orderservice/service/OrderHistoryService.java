package byramazanov.orderservice.service;

import byramazanov.core.types.OrderStatus;
import byramazanov.orderservice.dto.OrderHistory;

import java.util.List;
import java.util.UUID;

public interface OrderHistoryService {
    void add(UUID orderId, OrderStatus orderStatus);

    List<OrderHistory> findByOrderId(UUID orderId);
}
