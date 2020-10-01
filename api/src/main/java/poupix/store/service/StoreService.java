package poupix.store.service;

import de.huxhorn.sulky.ulid.ULID;
import java.util.List;
import poupix.store.dto.StoreDto;
import poupix.store.transport.http.request.CreateStoreRequest;

public interface StoreService {

  String createStore(CreateStoreRequest createStoreRequest);

  StoreDto getStore(ULID.Value storeId);

  List<StoreDto> getNearbyStores(Double lat, Double lon);
}
