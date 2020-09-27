package poupix.stores.transport.http.response;

import java.awt.*;
import java.util.Map;
import lombok.Builder;
import lombok.Value;
import poupix.stores.dal.model.Coordinates;

@Value
@Builder
public class StoreResponse {
  String id;
  String name;
  String description;
  String address;
  Coordinates coordinates;
  Map<String, Double> cashback;
}
