package poupix.stores.transport.http.request;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import poupix.stores.dal.model.Coordinates;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class CreateStoreRequest {
  String name;
  String description;
  Coordinates coordinates;
  String address;
  Map<String, Double> cashback;
}
