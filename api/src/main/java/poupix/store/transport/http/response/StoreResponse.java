package poupix.store.transport.http.response;

import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Value;
import poupix.store.dal.model.Coordinates;

@Value
@Builder
public class StoreResponse {
  String id;
  String name;
  String description;
  String category;
  String address;
  Coordinates coordinates;
  List<String> pictures;
  Map<String, Double> cashback;
}
