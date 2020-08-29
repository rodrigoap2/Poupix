package poupix.person.dal;

import java.util.Optional;
import poupix.person.PersonDto;

public interface PersonFacade {

  String createPerson(PersonDto personDto);

  Optional<String> authenticate(String cpf, String password);

  PersonDto getPerson(String cpf);
}
