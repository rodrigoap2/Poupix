package poupix.person.transport.http.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PersonCreationResponse {
  String cpf;
}
