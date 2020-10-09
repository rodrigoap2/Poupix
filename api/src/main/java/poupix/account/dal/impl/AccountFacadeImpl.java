package poupix.account.dal.impl;

import de.huxhorn.sulky.ulid.ULID;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import poupix.account.dal.AccountFacade;
import poupix.account.dal.dao.AccountRepository;
import poupix.account.dal.dao.GoalRepository;
import poupix.account.dal.model.Account;
import poupix.account.dal.model.Goal;
import poupix.account.dto.AccountDto;
import poupix.account.dto.GoalDto;
import poupix.account.dto.RoundupDto;
import poupix.common.exception.NotFoundException;

@Singleton
public class AccountFacadeImpl implements AccountFacade {

  @Inject EntityManager entityManager;
  @Inject AccountRepository accountRepository;
  @Inject GoalRepository goalRepository;

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

  @Override
  @Transactional
  public AccountDto getGeneralInformation(String personId) {
    Account account =
        accountRepository
            .findByPersonId(personId)
            .orElseThrow(() -> new NotFoundException("Account not found."));

    Object lastYearQuery =
        entityManager
            .createNativeQuery(
                "SELECT sum(value * cashback + roundup) FROM payment WHERE date >= :lastTwelveMonths AND person_id = :personId")
            .setParameter("lastTwelveMonths", LocalDate.now().minusMonths(12))
            .setParameter("personId", personId)
            .getSingleResult();
    Double lastTwelveMonths;
    if (lastYearQuery == null) lastTwelveMonths = 0D;
    else lastTwelveMonths = ((BigDecimal) lastYearQuery).doubleValue();

    List<Goal> goals = account.getGoals();

    Double totalGoal =
        goals.stream().map(Goal::getTotalGoal).reduce(0D, (total, actual) -> total += actual);
    Double monthlyGoal =
        totalGoal / goals.stream().map(Goal::getTotalMonths).reduce(0, Math::max).doubleValue();
    if (monthlyGoal.isNaN()) monthlyGoal = 1D;
    Object monthProgressQuery =
        entityManager
            .createNativeQuery(
                "SELECT sum(value * cashback + roundup) FROM payment WHERE date >= :thisMonth AND person_id = :personId")
            .setParameter("thisMonth", LocalDate.now().withDayOfMonth(1))
            .setParameter("personId", personId)
            .getSingleResult();
    Double thisMonthProgress;
    if (monthProgressQuery == null) thisMonthProgress = 0D;
    else thisMonthProgress = ((BigDecimal) monthProgressQuery).doubleValue();
    Double thisMonthPercentage = thisMonthProgress / monthlyGoal;

    return AccountDto.builder()
        .id(ULID.parseULID(account.getId()))
        .balance(account.getBalance())
        .personId(account.getPerson().getId())
        .personName(account.getPerson().getName())
        .roundup(account.getRoundup())
        .roundupValue(account.getRoundupValue())
        .goals(
            account
                .getGoals()
                .stream()
                .map(
                    goal -> {
                      Double actualGoal =
                          (totalGoal == 0 || account.getBalance() == 0)
                              ? 0
                              : goal.getTotalGoal() / totalGoal * account.getBalance();
                      return GoalDto.builder()
                          .id(ULID.parseULID(goal.getId()))
                          .title(goal.getTitle())
                          .totalGoal(goal.getTotalGoal())
                          .actualGoal(actualGoal)
                          .beginDate(goal.getBeginDate())
                          .actualMonth(
                              Period.between(goal.getBeginDate(), LocalDate.now()).getMonths())
                          .totalMonths(goal.getTotalMonths())
                          .build();
                    })
                .collect(Collectors.toList()))
        .lastTwelveMonthsBalance(lastTwelveMonths)
        .thisMonthPercentage(thisMonthPercentage)
        .thisMonthProgress(thisMonthProgress)
        .build();
  }

  @Override
  @Transactional
  public RoundupDto getRoundupInfo(String personId) {
    Double totalRoundup =
        ((BigDecimal)
                entityManager
                    .createNativeQuery(
                        "SELECT sum(roundup) FROM payment WHERE person_id = :personId")
                    .setParameter("personId", personId)
                    .getSingleResult())
            .doubleValue();

    Double lastMonthRoundup =
        ((BigDecimal)
                entityManager
                    .createNativeQuery(
                        "SELECT sum(roundup) FROM payment WHERE date >= :lastMonth AND person_id = :personId")
                    .setParameter("lastMonth", LocalDate.now().minusMonths(1))
                    .setParameter("personId", personId)
                    .getSingleResult())
            .doubleValue();
    return RoundupDto.builder()
        .lastMonthRoundup(lastMonthRoundup)
        .totalRoundup(totalRoundup)
        .build();
  }

  @Override
  @Transactional
  public ULID.Value createGoal(GoalDto goalDto, String personId) {
    Account account =
        accountRepository
            .findByPersonId(personId)
            .orElseThrow(() -> new NotFoundException("Account not found."));
    Goal goal = new Goal();
    goal.setId(goalDto.getId().toString());
    goal.setAccount(account);
    goal.setBeginDate(goalDto.getBeginDate());
    goal.setTitle(goalDto.getTitle());
    goal.setTotalGoal(goalDto.getTotalGoal());
    goal.setTotalMonths(goalDto.getTotalMonths());
    return ULID.parseULID(goalRepository.save(goal).getId());
  }

  @Override
  @Transactional
  public void changeRoundup(Optional<Double> roundup, String personId) {
    Account account =
        accountRepository
            .findByPersonId(personId)
            .orElseThrow(() -> new NotFoundException("Account not found."));
    account.setRoundup(!roundup.isPresent());
    account.setRoundupValue(roundup.orElse(0D));
    accountRepository.update(account);
  }
}
