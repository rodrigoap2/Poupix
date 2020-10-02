package poupix.account.transport.http.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoundupResponse {
  Double lastMonth;
  Double total;
  Double roundup;
}
