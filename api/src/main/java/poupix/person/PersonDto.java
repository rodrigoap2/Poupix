package poupix.person;

import java.time.LocalDate;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class PersonDto {
  @NonNull String cpf;
  @NonNull String name;
  String email;
  String password;
  LocalDate birthDate;
}
