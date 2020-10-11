package poupix.store.transport.http.request;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import poupix.store.dal.model.Coordinates;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class CreateStoreRequest {
  String name;
  String description;
  String category;
  Coordinates coordinates;
  String address;
  List<String> pictures;
  Map<String, Double> cashback;
}
