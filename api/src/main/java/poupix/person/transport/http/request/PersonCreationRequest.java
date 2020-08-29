package poupix.person.transport.http.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PersonCreationRequest {
  @NonNull String cpf;
  @NonNull String name;
  @NonNull String email;
  @NonNull String password;
  @NonNull String birthDate;
}
