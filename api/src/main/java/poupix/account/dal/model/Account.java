package poupix.account.dal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import poupix.person.dal.model.Person;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Account {
  @NotNull @EqualsAndHashCode.Include @Id String id;

  @NotNull Double balance;
  @NotNull Boolean roundup;

  @NotNull
  @Column(name = "roundup_value")
  Double roundupValue;

  @NotNull
  @OneToOne(fetch = FetchType.LAZY)
  Person person;
}
