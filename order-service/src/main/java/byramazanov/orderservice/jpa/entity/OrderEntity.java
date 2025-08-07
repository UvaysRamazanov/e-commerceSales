package byramazanov.orderservice.jpa.entity;

import byramazanov.core.types.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "orders")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "customer_id")
    private UUID customerId;

    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "product_quantity")
    private Integer productQuantity;
}
