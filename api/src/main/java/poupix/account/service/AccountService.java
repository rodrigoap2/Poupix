package poupix.account.service;

import de.huxhorn.sulky.ulid.ULID;
import poupix.account.transport.http.CreateGoalRequest;
import poupix.account.transport.http.response.GeneralInformationResponse;

public interface AccountService {

  GeneralInformationResponse getGeneralInformation(String personId);

  ULID.Value createGoal(CreateGoalRequest createGoalRequest, String personId);
}
