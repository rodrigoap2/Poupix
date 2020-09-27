package poupix.stores.transport.http.response;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NearbyStoresResponse {
  List<StoreResponse> stores;
}
