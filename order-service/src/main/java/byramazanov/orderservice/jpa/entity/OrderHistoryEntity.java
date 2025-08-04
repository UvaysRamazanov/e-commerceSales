package byramazanov.orderservice.jpa.entity;

import byramazanov.core.types.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Table(name = "orders_history")
@Entity
@Setter
@Getter
public class OrderHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "created_at")
    private Timestamp createdAt;
}
