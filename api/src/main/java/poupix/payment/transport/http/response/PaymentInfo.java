package poupix.payment.transport.http.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PaymentInfo {
  String personName;
  Double cashback;
  Double roundup;
}
