package byramazanov.paymentsservice.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@Table(name = "payments")
@Entity
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "Order ID cannot be null")
    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @NotNull(message = "Product ID cannot be null")
    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @NotNull(message = "Product price cannot be null")
    @Positive(message = "Product price must be positive")
    @Digits(integer = 10, fraction = 0, message = "Invalid price format")
    @Min(value = 1, message = "Minimum productPrice is 1")
    // @Max
    @Column(name = "product_price", precision = 12, scale = 2, nullable = false)
    private BigDecimal productPrice;

    @NotNull(message = "Product quantity cannot be null")
    @Min(value = 1, message = "Minimum quantity is 1")
    // @Max(value = ?, message = "Maximum quantity is ?") // todo в соответствии с бизнес логикой
    @Column(name = "product_quantity", nullable = false)
    @Digits(integer = 10, fraction = 0, message = "Invalid price format")
    private Integer productQuantity;

}
