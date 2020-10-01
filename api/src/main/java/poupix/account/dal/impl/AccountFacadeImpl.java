package poupix.account.dal.impl;

import de.huxhorn.sulky.ulid.ULID;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import poupix.account.dal.AccountFacade;
import poupix.account.dal.dao.AccountRepository;
import poupix.account.dal.model.Account;
import poupix.account.dto.AccountDto;
import poupix.common.exception.NotFoundException;

@Singleton
public class AccountFacadeImpl implements AccountFacade {

  @Inject AccountRepository accountRepository;

  @Override
  @Transactional
  public AccountDto getPersonAccount(String personId) {
    Account account =
        accountRepository
            .findByPersonId(personId)
            .orElseThrow(() -> new NotFoundException("Account not found."));
    return AccountDto.builder()
        .id(ULID.parseULID(account.getId()))
        .balance(account.getBalance())
        .personId(account.getPerson().getId())
        .roundup(account.getRoundup())
        .roundupValue(account.getRoundupValue())
        .build();
  }
}
