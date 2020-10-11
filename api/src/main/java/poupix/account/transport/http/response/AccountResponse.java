package poupix.account.transport.http.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountResponse {
  String personName;
  Double balance;
  Double lastYear;
  Double revenue;
  Double scheduled;
}
