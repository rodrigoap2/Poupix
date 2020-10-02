package poupix.account.dto;

import de.huxhorn.sulky.ulid.ULID;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountDto {
  ULID.Value id;
  String personId;
  Double balance;
  Boolean roundup;
  Double roundupValue;
  List<GoalDto> goals;
  Double lastTwelveMonthsBalance;
  Double thisMonthProgress;
  Double thisMonthPercentage;
}
