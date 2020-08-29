package poupix.person.transport.http.response;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class PersonResponse {
  @NonNull String cpf;
  @NonNull String name;
}
