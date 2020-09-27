package poupix.stores.service.impl;

import com.google.common.collect.ImmutableList;
import de.huxhorn.sulky.ulid.ULID;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import poupix.common.exception.BadRequestException;
import poupix.stores.dal.client.CouchbaseClient;
import poupix.stores.dal.model.Coordinates;
import poupix.stores.dto.StoreDto;
import poupix.stores.service.StoreService;
import poupix.stores.transport.http.request.CreateStoreRequest;

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
