package byramazanov.orderservice.service;

import byramazanov.core.dto.Order;

public interface OrderService {
    Order placeOrder(Order order);
}
