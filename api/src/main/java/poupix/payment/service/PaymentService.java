package poupix.payment.service;

import de.huxhorn.sulky.ulid.ULID;
import poupix.payment.transport.http.request.PaymentRequest;
import poupix.payment.transport.http.response.PaymentInfo;

public interface PaymentService {

  PaymentInfo getPaymentInfo(ULID.Value storeId, String personId, Double value);

  void receivePayment(PaymentRequest paymentRequest);
}
