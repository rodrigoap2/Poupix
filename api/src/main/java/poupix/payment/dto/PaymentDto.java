package poupix.payment.dto;

import de.huxhorn.sulky.ulid.ULID;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PaymentDto {
  ULID.Value id;
  Double value;
  Integer parcel;
  LocalDateTime date;
  LocalDateTime liquidationDate;
  Double cashback;
  Double roundup;
  String storeId;
  String personId;
}
