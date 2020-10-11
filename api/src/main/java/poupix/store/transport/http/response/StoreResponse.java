package poupix.store.transport.http.response;

import lombok.Builder;
import lombok.Value;
import poupix.store.dal.model.Coordinates;

@Value
@Builder
public class StoreResponse {
  String id;
  String name;
  String description;
  String type;
  String address;
  Coordinates coordinates;
  Object pictures;
  Object cashback;
}
