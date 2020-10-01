package poupix.payment.dal.dao;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import poupix.payment.dal.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {}
