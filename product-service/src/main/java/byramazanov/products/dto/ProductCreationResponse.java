package byramazanov.products.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreationResponse {
    private UUID id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
