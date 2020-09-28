package poupix.payment.service.impl;

import com.google.common.collect.ImmutableList;
import de.huxhorn.sulky.ulid.ULID;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import poupix.account.dal.AccountFacade;
import poupix.account.dto.AccountDto;
import poupix.common.exception.BadRequestException;
import poupix.payment.dal.PaymentFacade;
import poupix.payment.dto.PaymentDto;
import poupix.payment.service.PaymentService;
import poupix.payment.transport.http.request.PaymentRequest;
import poupix.payment.transport.http.response.PaymentInfo;
import poupix.person.PersonDto;
import poupix.person.dal.PersonFacade;
import poupix.store.dto.StoreDto;
import poupix.store.service.StoreService;

@Singleton
public class PaymentServiceImpl implements PaymentService {

  @Inject PaymentFacade paymentFacade;
  @Inject AccountFacade accountFacade;
  @Inject PersonFacade personFacade;
  @Inject StoreService storeService;

  @Override
  public PaymentInfo getPaymentInfo(ULID.Value storeId, String personId, Double value) {
    StoreDto storeDto = storeService.getStore(storeId);
    List<String> weekDays =
        ImmutableList.of(
            "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday");
    AccountDto accountDto = accountFacade.getPersonAccount(personId);
    PersonDto personDto = personFacade.getPerson(personId);

    Double cashback =
        storeDto.getCashback().get(weekDays.get(LocalDate.now().getDayOfWeek().ordinal()));
    Double ceil = Math.ceil((1 - cashback) * value);
    return PaymentInfo.builder()
        .personName(personDto.getName())
        .cashback(cashback)
        .roundup(
            accountDto.getRoundup() ? ceil - (1 - cashback) * value : accountDto.getRoundupValue())
        .build();
  }

  @Override
  public void receivePayment(PaymentRequest paymentRequest) {
    if (paymentRequest.getStoreId().length() != 26)
      throw new BadRequestException("Invalid store ID.");
    if (paymentRequest.getPersonId().length() != 11)
      throw new BadRequestException("Invalid person ID.");

    PaymentInfo paymentInfo =
        this.getPaymentInfo(
            ULID.parseULID(paymentRequest.getStoreId()),
            paymentRequest.getPersonId(),
            paymentRequest.getValue());
    LocalDateTime localDateTime = LocalDateTime.now();
    PaymentDto paymentDto =
        PaymentDto.builder()
            .id(new ULID().nextValue())
            .cashback(paymentInfo.getCashback())
            .date(localDateTime)
            .liquidationDate(paymentRequest.getLiquidationDate())
            .parcel(paymentRequest.getParcel())
            .value(paymentRequest.getValue())
            .roundup(paymentInfo.getRoundup())
            .personId(paymentRequest.getPersonId())
            .storeId(paymentRequest.getStoreId())
            .build();
    paymentFacade.receivePayment(paymentDto);
  }
}
