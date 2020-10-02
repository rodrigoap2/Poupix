package poupix.account.dal;

import de.huxhorn.sulky.ulid.ULID;
import poupix.account.dto.AccountDto;
import poupix.account.dto.GoalDto;
import poupix.account.dto.RoundupDto;

public interface AccountFacade {

  AccountDto getPersonAccount(String personId);

  AccountDto getGeneralInformation(String personId);

  RoundupDto getRoundupInfo(String personId);

  ULID.Value createGoal(GoalDto goalDto, String personId);
}
