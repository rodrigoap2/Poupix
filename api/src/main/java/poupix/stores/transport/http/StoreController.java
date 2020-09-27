package poupix.stores.transport.http;

import de.huxhorn.sulky.ulid.ULID;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import poupix.common.exception.BadRequestException;
import poupix.common.transport.http.response.CreationResponse;
import poupix.stores.dto.StoreDto;
import poupix.stores.service.StoreService;
import poupix.stores.transport.http.request.CreateStoreRequest;
import poupix.stores.transport.http.response.NearbyStoresResponse;
import poupix.stores.transport.http.response.StoreResponse;

@Controller("stores")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class StoreController {
  @Inject StoreService storeService;

  @Post
  public HttpResponse<CreationResponse> createStore(@Body CreateStoreRequest createStoreRequest) {
    String storeId = storeService.createStore(createStoreRequest);
    return HttpResponse.created(CreationResponse.builder().id(storeId).build());
  }

  @Get("/{id}")
  public HttpResponse<StoreResponse> getStore(@PathVariable @NotNull String id) {
    if (id.length() < 26) throw new BadRequestException("Invalid store ID.");
    StoreDto storeDto = storeService.getStore(ULID.parseULID(id));
    StoreResponse storeResponse =
        StoreResponse.builder()
            .id(storeDto.getId().toString())
            .name(storeDto.getName())
            .description(storeDto.getDescription())
            .address(storeDto.getAddress())
            .coordinates(storeDto.getCoordinates())
            .cashback(storeDto.getCashback())
            .build();
    return HttpResponse.ok(storeResponse);
  }

  @Get("/nearby/{lat}/{lon}")
  public HttpResponse<NearbyStoresResponse> getNearbyStores(
      @PathVariable @NotNull Double lat, @PathVariable @NotNull Double lon) {
    List<StoreDto> storeDtos = storeService.getNearbyStores(lat, lon);
    return HttpResponse.ok(
        NearbyStoresResponse.builder()
            .stores(
                storeDtos
                    .stream()
                    .map(
                        storeDto ->
                            StoreResponse.builder()
                                .id(storeDto.getId().toString())
                                .name(storeDto.getName())
                                .description(storeDto.getDescription())
                                .address(storeDto.getAddress())
                                .coordinates(storeDto.getCoordinates())
                                .cashback(storeDto.getCashback())
                                .build())
                    .collect(Collectors.toList()))
            .build());
  }
}
