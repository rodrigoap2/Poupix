package poupix.account.transport.http.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GeneralInformationResponse {
  RoundupResponse roundup;
  GoalsResponse goals;
  AccountResponse account;
}
