package poupix.account.dal.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Goal {
  @Id @NotNull @EqualsAndHashCode.Include String id;

  @NotNull String title;

  @NotNull
  @Column(name = "total_goal")
  Double totalGoal;

  @NotNull
  @Column(name = "begin_date")
  LocalDate beginDate;

  @NotNull
  @Column(name = "total_months")
  Integer totalMonths;

  @ToString.Exclude
  @ManyToOne(fetch = FetchType.LAZY)
  Account account;
}
