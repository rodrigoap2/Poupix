package poupix.person.dal.dao;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import java.util.Optional;
import poupix.person.dal.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

  @Query(
      value =
          "SELECT * FROM Person p WHERE p.id = :id AND p.password = crypt(:password, p.password)",
      nativeQuery = true)
  Optional<Person> findByIdAndPassword(String id, String password);
}
