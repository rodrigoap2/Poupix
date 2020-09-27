package poupix.stores.dal.model;

import com.couchbase.client.java.json.JsonObject;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Store {
  String name;
  String description;
  Coordinates coordinates;
  String address;
  Map<String, Double> cashback;

  public static Store parseFromJSON(JsonObject jsonObject) {
    Store store = new Store();
    store.setName((String) jsonObject.get("name"));
    store.setDescription((String) jsonObject.get("description"));
    store.setAddress((String) jsonObject.get("address"));
    JsonObject coordinates = jsonObject.getObject("coordinates");
    Coordinates storeCoordinates =
        new Coordinates(coordinates.getDouble("lon"), coordinates.getDouble("lat"));
    store.setCoordinates(storeCoordinates);
    store.setCashback(
        jsonObject
            .getObject("cashback")
            .toMap()
            .entrySet()
            .stream()
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey, value -> Double.valueOf(value.getValue().toString()))));
    return store;
  }
}
