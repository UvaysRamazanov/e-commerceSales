package byramazanov.paymentsservice.service;

import byramazanov.core.dto.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> findAll();

    Payment process(Payment payment);
}
