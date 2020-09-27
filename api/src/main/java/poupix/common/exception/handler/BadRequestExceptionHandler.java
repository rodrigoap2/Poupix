package poupix.common.exception.handler;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import javax.inject.Singleton;
import poupix.common.exception.BadRequestException;
import poupix.common.exception.response.ExceptionResponse;

@Produces
@Singleton
@Requires(classes = {BadRequestException.class, ExceptionHandler.class})
public class BadRequestExceptionHandler
    implements ExceptionHandler<BadRequestException, HttpResponse> {
  @Override
  public HttpResponse handle(HttpRequest request, BadRequestException exception) {
    return HttpResponse.badRequest(new ExceptionResponse(exception.getMessage()));
  }
}
