package poupix.person.dal.model;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import poupix.account.dal.model.Account;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person {
  @NonNull @Id @EqualsAndHashCode.Include String id;

  @NonNull String password;

  @NonNull String name;

  @NonNull String email;

  @NonNull
  @Column(name = "birth_date")
  LocalDate birthDate;

  @ToString.Exclude
  @OneToOne(
      mappedBy = "person",
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      optional = false)
  Account account;

  public void setAccount(Account account) {
    if (account == null) {
      if (this.account != null) {
        this.account.setPerson(null);
      }
    } else {
      account.setPerson(this);
    }
    this.account = account;
  }
}
