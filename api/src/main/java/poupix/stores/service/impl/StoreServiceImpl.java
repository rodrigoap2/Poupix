package poupix.stores.service.impl;

import javax.inject.Inject;
import javax.inject.Singleton;
import poupix.stores.dal.client.CouchbaseClient;
import poupix.stores.service.StoreService;

@Singleton
public class StoreServiceImpl implements StoreService {
  @Inject CouchbaseClient couchbaseClient;

  public String createStore() {
    return couchbaseClient.createStore();
  }
}
