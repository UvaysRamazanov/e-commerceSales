package byramazanov.products.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@Table(name = "products")
@Entity
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

}