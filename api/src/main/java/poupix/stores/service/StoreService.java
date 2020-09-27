package poupix.stores.service;

import de.huxhorn.sulky.ulid.ULID;
import java.util.List;
import poupix.stores.dto.StoreDto;
import poupix.stores.transport.http.request.CreateStoreRequest;

public interface StoreService {

  String createStore(CreateStoreRequest createStoreRequest);

  StoreDto getStore(ULID.Value storeId);

  List<StoreDto> getNearbyStores(Double lat, Double lon);
}
