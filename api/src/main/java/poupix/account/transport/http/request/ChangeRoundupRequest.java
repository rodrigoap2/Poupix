package poupix.account.transport.http.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ChangeRoundupRequest {
  Double roundup;
}
