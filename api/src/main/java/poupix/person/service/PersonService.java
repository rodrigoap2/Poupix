package poupix.person.service;

import java.util.Optional;
import poupix.person.PersonDto;
import poupix.person.transport.http.request.PersonCreationRequest;

public interface PersonService {

  String createPerson(PersonCreationRequest personCreationRequest);

  Optional<String> authenticate(String cpf, String password);

  PersonDto getPerson(String cpf);
}
