package byramazanov.orderservice.dto;

import byramazanov.core.types.OrderStatus;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderHistory {
    private UUID id;
    private UUID orderId;
    private OrderStatus status;
    private Instant createdAt;
}
