package byramazanov.core.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardProcessRequest {
    @NotNull
    @Positive
    private BigInteger creditCardNumber;

    @NotNull
    @Positive
    private BigDecimal paymentAmount;
}
