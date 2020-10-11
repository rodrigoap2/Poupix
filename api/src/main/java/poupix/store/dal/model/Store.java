package poupix.store.dal.model;

import com.couchbase.client.java.json.JsonArray;
import com.couchbase.client.java.json.JsonObject;
import java.util.List;
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
  String category;
  Coordinates coordinates;
  String address;
  List<String> pictures;
  Map<String, Double> cashback;

  public static Store parseFromJSON(JsonObject jsonObject) {
    Store store = new Store();
    store.setName((String) jsonObject.get("name"));
    store.setDescription((String) jsonObject.get("description"));
    store.setCategory((String) jsonObject.get("category"));
    store.setAddress((String) jsonObject.get("address"));
    store.setPictures(
        ((JsonArray) jsonObject.get("pictures"))
            .toList()
            .stream()
            .map(Object::toString)
            .collect(Collectors.toList()));
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
