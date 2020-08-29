package poupix.person.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;
import poupix.person.PersonDto;
import poupix.person.dal.PersonFacade;
import poupix.person.service.PersonService;
import poupix.person.transport.http.request.PersonCreationRequest;

@Singleton
public class PersonServiceImpl implements PersonService {

  @Inject PersonFacade personFacade;

  @Override
  public String createPerson(PersonCreationRequest personCreationRequest) {
    PersonDto personDto =
        PersonDto.builder()
            .cpf(personCreationRequest.getCpf())
            .name(personCreationRequest.getName())
            .email(personCreationRequest.getEmail())
            .password(personCreationRequest.getPassword())
            .birthDate(
                LocalDate.parse(personCreationRequest.getBirthDate(), DateTimeFormatter.ISO_DATE))
            .build();
    return personFacade.createPerson(personDto);
  }

  @Override
  public Optional<String> authenticate(String cpf, String password) {
    return personFacade.authenticate(cpf, password);
  }

  @Override
  public PersonDto getPerson(String cpf) {
    return personFacade.getPerson(cpf);
  }
}
