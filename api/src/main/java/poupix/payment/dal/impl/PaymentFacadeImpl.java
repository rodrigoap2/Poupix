package poupix.payment.dal.impl;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import poupix.account.dal.dao.AccountRepository;
import poupix.common.exception.NotFoundException;
import poupix.payment.dal.PaymentFacade;
import poupix.payment.dal.dao.PaymentRepository;
import poupix.payment.dal.model.Payment;
import poupix.payment.dto.PaymentDto;
import poupix.person.dal.dao.PersonRepository;
import poupix.person.dal.model.Person;

@Singleton
public class PaymentFacadeImpl implements PaymentFacade {

  @Inject PaymentRepository paymentRepository;
  @Inject AccountRepository accountRepository;
  @Inject PersonRepository personRepository;

  @Override
  @Transactional
  public void receivePayment(PaymentDto paymentDto) {
    Person person =
        personRepository
            .findById(paymentDto.getPersonId())
            .orElseThrow(() -> new NotFoundException("Person Not Found."));

    Payment payment = new Payment();
    payment.setId(paymentDto.getId().toString());
    payment.setCashback(paymentDto.getCashback());
    payment.setDate(paymentDto.getDate());
    payment.setLiquidationDate(paymentDto.getLiquidationDate());
    payment.setParcel(paymentDto.getParcel());
    payment.setRoundup(paymentDto.getRoundup());
    payment.setValue(paymentDto.getValue());
    payment.setPersonId(paymentDto.getPersonId());
    payment.setStoreId(paymentDto.getStoreId());

    Double userValue = (payment.getValue() * payment.getCashback()) + payment.getRoundup();
    accountRepository.updateBalance(userValue, person.getAccount().getId());
    paymentRepository.save(payment);
  }
}
