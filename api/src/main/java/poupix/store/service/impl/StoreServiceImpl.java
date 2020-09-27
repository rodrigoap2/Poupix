package poupix.store.service.impl;

import com.google.common.collect.ImmutableList;
import de.huxhorn.sulky.ulid.ULID;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import poupix.common.exception.BadRequestException;
import poupix.store.dal.client.CouchbaseClient;
import poupix.store.dal.model.Coordinates;
import poupix.store.dto.StoreDto;
import poupix.store.service.StoreService;
import poupix.store.transport.http.request.CreateStoreRequest;

@Singleton
public class StoreServiceImpl implements StoreService {
  @Inject CouchbaseClient couchbaseClient;

  public String createStore(CreateStoreRequest createStoreRequest) {
    if (!createStoreRequest
        .getCashback()
        .keySet()
        .containsAll(
            ImmutableList.of(
                "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"))) {
      throw new BadRequestException("Missing week day(s).");
    }

    StoreDto storeDto =
        StoreDto.builder()
            .id(new ULID().nextValue())
            .name(createStoreRequest.getName())
            .address(createStoreRequest.getAddress())
            .coordinates(createStoreRequest.getCoordinates())
            .cashback(createStoreRequest.getCashback())
            .pictures(createStoreRequest.getPictures())
            .description(createStoreRequest.getDescription())
            .build();
    return couchbaseClient.createStore(storeDto);
  }

  @Override
  public StoreDto getStore(ULID.Value storeId) {
    return couchbaseClient.getStore(storeId);
  }

  @Override
  public List<StoreDto> getNearbyStores(Double lat, Double lon) {
    Coordinates coordinates = new Coordinates(lon, lat);
    return couchbaseClient.getNearbyStores(coordinates);
  }
}
