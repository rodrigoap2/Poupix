package poupix.stores.dal.client;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.json.JsonObject;
import io.micronaut.context.annotation.Value;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class CouchbaseClient {
  Cluster cluster;
  Bucket bucket;

  @Value("${couchbase.user}")
  String couchbaseUser;

  @Value("${couchbase.password}")
  String couchbasePassword;

  @EventListener
  public void onStartup(StartupEvent event) {
    log.info("Connecting to couchbase.");
    cluster = Cluster.connect("couchbase", couchbaseUser, couchbasePassword);
    bucket = cluster.bucket("stores");
  }

  public String createStore() {
    final JsonObject ratingJSON =
        JsonObject.create()
            .put("movie_id", "filminho-delicia")
            .put("user_id", "daniel")
            .put("value", "filme-tocando");

    try {
      bucket.defaultCollection().insert("filminho-delicia", ratingJSON);
      System.out.println("OK");
    } catch (Exception e) {
      System.out.println("ERROR: " + e.getMessage());
    }
    return "filminho-delicia";
  }
}
