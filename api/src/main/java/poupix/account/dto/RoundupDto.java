package poupix.account.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoundupDto {
  Double lastMonthRoundup;
  Double totalRoundup;
}
