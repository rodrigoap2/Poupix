package poupix.person.dal.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
  @NonNull @Id String id;

  @NonNull String password;

  @NonNull String name;

  @NonNull String email;

  @NonNull
  @Column(name = "birth_date")
  LocalDate birthDate;
}
