package byramazanov.core.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardProcessRequest {
    @org.jetbrains.annotations.NotNull
    @Positive
    private BigInteger creditCardNumber;

    @NotNull
    @Positive
    private BigDecimal paymentAmount;
}
