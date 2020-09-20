package poupix.stores.transport.http;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import javax.inject.Inject;
import poupix.stores.service.StoreService;

@Controller("stores")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class StoreController {
  @Inject StoreService storeService;

  @Post
  public String helloWorld() {
    String storeId = storeService.createStore();
    return "Hello world :D";
  }
}
