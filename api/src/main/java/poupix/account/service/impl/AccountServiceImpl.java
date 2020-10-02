package poupix.account.service.impl;

import de.huxhorn.sulky.ulid.ULID;
import java.time.LocalDate;
import java.util.stream.Collectors;
import javax.inject.Inject;
import poupix.account.dal.AccountFacade;
import poupix.account.dto.AccountDto;
import poupix.account.dto.GoalDto;
import poupix.account.dto.RoundupDto;
import poupix.account.service.AccountService;
import poupix.account.transport.http.CreateGoalRequest;
import poupix.account.transport.http.response.AccountResponse;
import poupix.account.transport.http.response.GeneralInformationResponse;
import poupix.account.transport.http.response.GoalResponse;
import poupix.account.transport.http.response.GoalsResponse;
import poupix.account.transport.http.response.RoundupResponse;

public class AccountServiceImpl implements AccountService {

  @Inject AccountFacade accountFacade;

  @Override
  public GeneralInformationResponse getGeneralInformation(String personId) {
    AccountDto accountDto = accountFacade.getGeneralInformation(personId);
    RoundupDto roundupDto = accountFacade.getRoundupInfo(personId);
    return GeneralInformationResponse.builder()
        .roundup(
            RoundupResponse.builder()
                .lastMonth(roundupDto.getLastMonthRoundup())
                .total(roundupDto.getTotalRoundup())
                .roundup(accountDto.getRoundup() ? null : accountDto.getRoundupValue())
                .build())
        .goals(
            GoalsResponse.builder()
                .thisMonthPercentage(accountDto.getThisMonthPercentage())
                .thisMonthProgress(accountDto.getThisMonthProgress())
                .goalsInfo(
                    accountDto
                        .getGoals()
                        .stream()
                        .map(
                            goalDto ->
                                GoalResponse.builder()
                                    .name(goalDto.getTitle())
                                    .actualMonth(goalDto.getActualMonth())
                                    .actualValue(goalDto.getActualGoal())
                                    .totalMonths(goalDto.getTotalMonths())
                                    .totalValue(goalDto.getTotalGoal())
                                    .build())
                        .collect(Collectors.toList()))
                .build())
        .account(
            AccountResponse.builder()
                .balance(accountDto.getBalance())
                .lastYear(accountDto.getLastTwelveMonthsBalance())
                .revenue(0D)
                .scheduled(0D)
                .build())
        .build();
  }

  @Override
  public ULID.Value createGoal(CreateGoalRequest createGoalRequest, String personId) {
    GoalDto goalDto =
        GoalDto.builder()
            .id(new ULID().nextValue())
            .totalGoal(createGoalRequest.getTotalGoal())
            .beginDate(LocalDate.now())
            .title(createGoalRequest.getTitle())
            .totalMonths(createGoalRequest.getTotalMonths())
            .build();
    return accountFacade.createGoal(goalDto, personId);
  }
}
