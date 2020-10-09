package poupix.account.transport.http.request;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class CreateGoalRequest {
  @NotNull String title;
  @NotNull Double totalGoal;
  @NotNull Integer totalMonths;
}
