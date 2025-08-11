package byramazanov.paymentsservice.service;

import byramazanov.core.dto.Payment;
import byramazanov.paymentsservice.jpa.entity.PaymentEntity;
import byramazanov.paymentsservice.jpa.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {
    public static final String SAMPLE_CREDIT_CARD_NUMBER = "374245455400126";
    private final PaymentRepository paymentRepository;
    private final CreditCardProcessorRemoteService ccpRemoteService;

    @Override
    public Payment process(Payment payment) {
        BigDecimal totalPrice = payment.getProductPrice()
                .multiply(new BigDecimal(payment.getProductQuantity()));
        ccpRemoteService.process(new BigInteger(SAMPLE_CREDIT_CARD_NUMBER), totalPrice);
        PaymentEntity paymentEntity = new PaymentEntity();
        BeanUtils.copyProperties(payment, paymentEntity);
        paymentRepository.save(paymentEntity);

        var processedPayment = new Payment();
        BeanUtils.copyProperties(payment, processedPayment);
        processedPayment.setId(paymentEntity.getId());
        return processedPayment;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Payment> findAll() {
        return paymentRepository.findAll().stream()
                .map(this::mapToPaymentDto)
                .collect(Collectors.toList());
    }

    private Payment mapToPaymentDto(PaymentEntity entity) {
        return Payment.builder()
                .id(entity.getId())
                .orderId(entity.getOrderId())
                .productId(entity.getProductId())
                .productPrice(entity.getProductPrice())
                .productQuantity(entity.getProductQuantity())
                .build();
    }

}
