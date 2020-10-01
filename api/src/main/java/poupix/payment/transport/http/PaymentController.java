package poupix.payment.transport.http;

import de.huxhorn.sulky.ulid.ULID;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import poupix.common.exception.BadRequestException;
import poupix.payment.service.PaymentService;
import poupix.payment.transport.http.request.PaymentRequest;
import poupix.payment.transport.http.response.PaymentInfo;

@Controller("/payments")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class PaymentController {

  @Inject PaymentService paymentService;

  @Get("/{storeId}/{personId}/{value}")
  public HttpResponse<PaymentInfo> paymentInfo(
      @PathVariable @NotNull String storeId, @PathVariable @NotNull String personId, Double value) {
    if (storeId.length() != 26) throw new BadRequestException("Invalid store ID.");
    if (personId.length() != 11) throw new BadRequestException("Invalid person ID.");
    PaymentInfo paymentInfo =
        paymentService.getPaymentInfo(ULID.parseULID(storeId), personId, value);
    return HttpResponse.ok(paymentInfo);
  }

  @Post
  public HttpResponse receivePayment(@Body @NotNull PaymentRequest paymentRequest) {
    paymentService.receivePayment(paymentRequest);
    return HttpResponse.ok();
  }
}
