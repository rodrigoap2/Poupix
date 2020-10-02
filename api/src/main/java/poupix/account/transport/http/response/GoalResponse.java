package poupix.account.transport.http.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GoalResponse {
  String name;
  Double totalValue;
  Double actualValue;
  Integer actualMonth;
  Integer totalMonths;
}
