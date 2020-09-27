package poupix.stores.dto;

import de.huxhorn.sulky.ulid.ULID;
import java.util.Map;
import lombok.Builder;
import lombok.Value;
import poupix.stores.dal.model.Coordinates;

@Value
@Builder
public class StoreDto {
  ULID.Value id;
  String name;
  String description;
  Coordinates coordinates;
  String address;
  Map<String, Double> cashback;
}
