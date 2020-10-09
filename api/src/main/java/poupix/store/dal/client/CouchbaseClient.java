package poupix.store.dal.client;

import com.couchbase.client.core.cnc.tracing.ThresholdRequestTracer;
import com.couchbase.client.core.endpoint.CircuitBreakerConfig;
import com.couchbase.client.core.env.IoConfig;
import com.couchbase.client.core.env.PasswordAuthenticator;
import com.couchbase.client.core.env.ThresholdRequestTracerConfig;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.ClusterOptions;
import com.couchbase.client.java.env.ClusterEnvironment;
import com.couchbase.client.java.search.queries.GeoDistanceQuery;
import com.couchbase.client.java.search.result.SearchRow;
import de.huxhorn.sulky.ulid.ULID;
import io.micronaut.context.annotation.Value;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import poupix.store.dal.model.Coordinates;
import poupix.store.dal.model.Store;
import poupix.store.dto.StoreDto;

@Slf4j
@Singleton
public class CouchbaseClient {
  Cluster cluster;
  final String STORES_BUCKET = "stores";
  final String STORES_GEOSPATIAL_IDX = "store-geospatial-idx";

  @Value("${couchbase.url}")
  String couchbaseURL;

  @Value("${couchbase.user}")
  String couchbaseUser;

  @Value("${couchbase.password}")
  String couchbasePassword;

  @EventListener
  public void onStartup(StartupEvent event) {
    log.info("Connecting to couchbase.");
    PasswordAuthenticator authenticator =
        PasswordAuthenticator.create(couchbaseUser, couchbasePassword);
    ClusterEnvironment environment =
        ClusterEnvironment.builder()
            .ioConfig(
                IoConfig.idleHttpConnectionTimeout(Duration.ofSeconds(2))
                    .searchCircuitBreakerConfig(CircuitBreakerConfig.builder()))
            .requestTracer(
                ThresholdRequestTracer.create(
                    null,
                    ThresholdRequestTracerConfig.builder()
                        .queryThreshold(Duration.ofSeconds(12))
                        .build()))
            .build();
    ClusterOptions clusterOptions =
        ClusterOptions.clusterOptions(authenticator).environment(environment);
    cluster = Cluster.connect(couchbaseURL, clusterOptions);
  }

  public String createStore(StoreDto storeDto) {
    Store store =
        new Store(
            storeDto.getName(),
            storeDto.getDescription(),
            storeDto.getCoordinates(),
            storeDto.getAddress(),
            storeDto.getPictures(),
            storeDto.getCashback());
    cluster.bucket(STORES_BUCKET).defaultCollection().insert(storeDto.getId().toString(), store);
    return storeDto.getId().toString();
  }

  public StoreDto getStore(ULID.Value storeId) {
    Store store =
        Store.parseFromJSON(
            cluster
                .bucket(STORES_BUCKET)
                .defaultCollection()
                .get(storeId.toString())
                .contentAsObject());
    return StoreDto.builder()
        .id(storeId)
        .name(store.getName())
        .description(store.getDescription())
        .address(store.getAddress())
        .coordinates(store.getCoordinates())
        .pictures(store.getPictures())
        .cashback(store.getCashback())
        .build();
  }

  public List<StoreDto> getNearbyStores(Coordinates coordinates) {
    GeoDistanceQuery geoDistanceQuery =
        new GeoDistanceQuery(coordinates.getLon(), coordinates.getLat(), "10000m");
    return cluster
        .searchQuery(STORES_GEOSPATIAL_IDX, geoDistanceQuery)
        .rows()
        .stream()
        .map(SearchRow::id)
        .map(id -> this.getStore(ULID.parseULID(id)))
        .collect(Collectors.toList());
  }
}
