package poupix.account.transport.http;

import de.huxhorn.sulky.ulid.ULID;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import poupix.account.service.AccountService;
import poupix.account.transport.http.request.ChangeRoundupRequest;
import poupix.account.transport.http.request.CreateGoalRequest;
import poupix.account.transport.http.response.GeneralInformationResponse;
import poupix.common.transport.http.response.CreationResponse;

@Controller("/accounts")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class AccountController {

  @Inject AccountService accountService;

  @Get("/general-information")
  public HttpResponse<GeneralInformationResponse> getGeneralInformation(
      @NotNull Authentication authentication) {
    String personId = authentication.getName();
    GeneralInformationResponse response = accountService.getGeneralInformation(personId);
    return HttpResponse.ok(response);
  }

  @Post("/goal")
  public HttpResponse<CreationResponse> createGoal(
      @NotNull CreateGoalRequest createGoalRequest, @NotNull Authentication authentication) {
    String personId = authentication.getName();
    ULID.Value goalId = accountService.createGoal(createGoalRequest, personId);
    return HttpResponse.created(CreationResponse.builder().id(goalId.toString()).build());
  }

  @Put("roundup")
  public HttpResponse changeRoundup(
      @NotNull ChangeRoundupRequest changeRoundupRequest, @NotNull Authentication authentication) {
    String personId = authentication.getName();
    accountService.changeRoundup(changeRoundupRequest, personId);
    return HttpResponse.ok();
  }
}
