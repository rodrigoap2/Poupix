package poupix.account.dal.dao;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import poupix.account.dal.model.Goal;

@Repository
public interface GoalRepository extends JpaRepository<Goal, String> {}
