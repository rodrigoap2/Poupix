package poupix.common.exception.handler;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import javax.inject.Singleton;
import poupix.common.exception.NotFoundException;
import poupix.common.exception.response.ExceptionResponse;

@Produces
@Singleton
@Requires(classes = {NotFoundException.class, ExceptionHandler.class})
public class NotFoundExceptionHandler implements ExceptionHandler<NotFoundException, HttpResponse> {
  @Override
  public HttpResponse handle(HttpRequest request, NotFoundException exception) {
    return HttpResponse.notFound(new ExceptionResponse(exception.getMessage()));
  }
}
