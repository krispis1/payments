package com.banking.payments.service;

import com.banking.payments.enums.payment.PaymentType;
import com.banking.payments.model.payment.Payment;
import com.banking.payments.repository.PaymentRepository;
import com.banking.payments.util.CurrencyUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class PaymentServiceTest {
    @Autowired
    PaymentService paymentService;

    @Autowired
    PaymentRepository paymentRepository;

    @BeforeEach
    public void beforeEach() {
        paymentRepository.deleteAll();
    }

    @Test
    public void createPaymentOneValid() {
        paymentService.savePayment(null, PaymentType.ONE, 10.1, "123", "XYZ", "Payment", null);

        List<Payment> payments = paymentRepository.findAll();

        assertEquals(payments.size(), 1);
        assertEquals(payments.get(0).getCurrency(), CurrencyUtil.Currency.EUR);
    }

    @Test
    public void createPaymentTwoValid() {
        paymentService.savePayment(null, PaymentType.TWO, 10.1, "123", "XYZ", null, null);

        List<Payment> payments = paymentRepository.findAll();

        assertEquals(payments.size(), 1);
        assertEquals(payments.get(0).getCurrency(), CurrencyUtil.Currency.USD);
    }

    @Test
    public void createPaymentThreeValid() {
        paymentService.savePayment(CurrencyUtil.Currency.EUR, PaymentType.THREE, 10.1, "123", "XYZ", "Payment", "0987654321");

        List<Payment> payments = paymentRepository.findAll();

        assertEquals(payments.size(), 1);
        assertEquals(payments.get(0).getCurrency(), CurrencyUtil.Currency.EUR);
    }

    @Test
    public void createPaymentInvalid() {
        try {
            paymentService.savePayment(null, PaymentType.THREE, 10.1, "123", "XYZ", "Payment", "0987654321");
        } catch (RuntimeException ex) {
            assertEquals("ConstraintViolationException: Validation failed for classes [com.banking.payments.model.payment.types.PaymentTypeThree] during persist time for groups [javax.validation.groups.Default, ]\n" +
                    "List of constraint violations:[\n" +
                    "\tConstraintViolationImpl{interpolatedMessage='must not be null', propertyPath=currency, rootBeanClass=class com.banking.payments.model.payment.types.PaymentTypeThree, messageTemplate='{javax.validation.constraints.NotNull.message}'}\n" +
                    "]" ,ExceptionUtils.getRootCauseMessage(ex));
        }

        List<Payment> payments = paymentRepository.findAll();
        assertEquals(payments.size(), 0);
    }
}
