package poupix.account.dto;

import de.huxhorn.sulky.ulid.ULID;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GoalDto {
  ULID.Value id;
  String title;
  Double totalGoal;
  Double actualGoal;
  LocalDate beginDate;
  Integer actualMonth;
  Integer totalMonths;
}
