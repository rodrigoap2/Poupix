package poupix.person.dal.impl;

import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import poupix.person.PersonDto;
import poupix.person.dal.PersonFacade;
import poupix.person.dal.dao.PersonRepository;
import poupix.person.dal.model.Person;

@Singleton
public class PersonFacadeImpl implements PersonFacade {

  @Inject PersonRepository personRepository;
  @Inject EntityManager entityManager;

  @Override
  @Transactional
  public String createPerson(PersonDto personDto) {
    int success =
        entityManager
            .createNativeQuery(
                "INSERT INTO person (id, name, email, birth_date, password) VALUES "
                    + "(:id,"
                    + " :name,"
                    + " :email,"
                    + " to_date(:birthDate, 'YYYY-MM-DD'),"
                    + " crypt(:password, gen_salt('bf')))")
            .setParameter("id", personDto.getCpf())
            .setParameter("name", personDto.getName())
            .setParameter("email", personDto.getEmail())
            .setParameter("birthDate", personDto.getBirthDate())
            .setParameter("password", personDto.getPassword())
            .executeUpdate();
    return success == 1 ? personDto.getCpf() : null;
  }

  @Override
  @Transactional
  public Optional<String> authenticate(String cpf, String password) {
    Optional<Person> person = personRepository.findByIdAndPassword(cpf, password);
    return Optional.ofNullable(person.orElse(new Person()).getId());
  }

  @Override
  @Transactional
  public PersonDto getPerson(String cpf) {
    Person person = personRepository.findById(cpf).orElse(new Person());
    return PersonDto.builder()
        .cpf(person.getId())
        .name(person.getName())
        .email(person.getEmail())
        .birthDate(person.getBirthDate())
        .build();
  }
}
