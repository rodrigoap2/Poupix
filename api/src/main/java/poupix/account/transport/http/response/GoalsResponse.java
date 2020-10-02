package poupix.account.transport.http.response;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GoalsResponse {
  Double thisMonthPercentage;
  Double thisMonthProgress;
  List<GoalResponse> goalsInfo;
}
