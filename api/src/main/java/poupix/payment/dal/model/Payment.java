package poupix.payment.dal.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Payment {
  @Id @NotNull @EqualsAndHashCode.Include String id;

  @NotNull Double value;
  @NotNull Integer parcel;
  @NotNull LocalDateTime date;

  @NotNull
  @Column(name = "liquidation_date")
  LocalDateTime liquidationDate;

  @NotNull Double cashback;
  @NotNull Double roundup;

  @NotNull
  @Column(name = "store_id")
  String storeId;

  @NotNull
  @Column(name = "person_id")
  String personId;
}
