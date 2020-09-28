package poupix.payment.transport.http.request;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PaymentRequest {
  Double value;
  Integer parcel;
  LocalDateTime liquidationDate;
  String storeId;
  String personId;
}
