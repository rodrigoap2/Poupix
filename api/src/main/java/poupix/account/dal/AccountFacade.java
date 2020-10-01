package poupix.account.dal;

import poupix.account.dto.AccountDto;

public interface AccountFacade {

  AccountDto getPersonAccount(String personId);
}
