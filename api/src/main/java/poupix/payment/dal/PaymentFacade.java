package poupix.payment.dal;

import poupix.payment.dto.PaymentDto;

public interface PaymentFacade {

  void receivePayment(PaymentDto paymentDto);
}
