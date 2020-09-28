package poupix.account.dal.dao;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import java.util.Optional;
import poupix.account.dal.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

  Optional<Account> findByPersonId(String personId);

  @Query(
      value = "UPDATE Account a SET a.balance = a.balance + :value WHERE a.id = :accountId",
      nativeQuery = true)
  void updateBalance(Double value, String accountId);
}
