package poupix.stores.transport.http;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller
public class StoreController {

  @Get
  public String helloWorld() {
    return "Hello world :D";
  }
}
