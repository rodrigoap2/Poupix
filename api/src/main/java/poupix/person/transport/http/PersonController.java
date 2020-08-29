package poupix.person.transport.http;

import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.utils.DefaultSecurityService;
import io.reactivex.Flowable;
import java.util.Collections;
import java.util.Optional;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import org.reactivestreams.Publisher;
import poupix.person.PersonDto;
import poupix.person.service.PersonService;
import poupix.person.transport.http.request.PersonCreationRequest;
import poupix.person.transport.http.response.PersonCreationResponse;
import poupix.person.transport.http.response.PersonResponse;

@Controller("persons")
public class PersonController implements AuthenticationProvider {

  @Inject PersonService personService;
  @Inject DefaultSecurityService defaultSecurityService;

  @Override
  public Publisher<AuthenticationResponse> authenticate(
      @Nullable HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
    String cpf = authenticationRequest.getIdentity().toString();
    String password = authenticationRequest.getSecret().toString();
    Optional<String> personCpf = personService.authenticate(cpf, password);
    if (personCpf.isPresent()) {
      UserDetails userDetails = new UserDetails(personCpf.get(), Collections.emptyList());
      return Flowable.just(userDetails);
    } else {
      return Flowable.just(new AuthenticationFailed("Wrong credentials"));
    }
  }

  @Post
  @Secured(SecurityRule.IS_ANONYMOUS)
  public HttpResponse<PersonCreationResponse> createPerson(
      @NotNull @Body PersonCreationRequest personCreationRequest) {
    String cpf = personService.createPerson(personCreationRequest);
    return HttpResponse.created(PersonCreationResponse.builder().cpf(cpf).build());
  }

  @Get
  public HttpResponse<PersonResponse> getPerson() {
    String cpf = defaultSecurityService.username().get();
    PersonDto personDto = personService.getPerson(cpf);
    return HttpResponse.ok(
        PersonResponse.builder().cpf(personDto.getCpf()).name(personDto.getName()).build());
  }
}
