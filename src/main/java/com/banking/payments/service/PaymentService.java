package com.banking.payments.service;

import com.banking.payments.model.payments.Payment;
import com.banking.payments.model.payments.types.PaymentTypeOne;
import com.banking.payments.model.payments.types.PaymentTypeThree;
import com.banking.payments.model.payments.types.PaymentTypeTwo;
import com.banking.payments.repository.PaymentRepository;
import com.banking.payments.util.CurrencyUtil.Currency;
import com.banking.payments.util.PaymentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional(rollbackOn = Exception.class)
    public Payment savePayment(Currency currency, PaymentType paymentType, Double amount, String debtorIban, String creditorIban, String details, String bicCode) throws ConstraintViolationException {
        switch (paymentType) {
            case ONE:
                return saveTypeOne(amount, debtorIban, creditorIban, details);
            case TWO:
                return saveTypeTwo(amount, debtorIban, creditorIban, details);
            case THREE:
                return saveTypeThree(currency, amount, debtorIban, creditorIban, bicCode);
            default:
                return null;
        }
    }

    private Payment saveTypeOne(Double amount, String debtorIban, String creditorIban, String details) throws ConstraintViolationException {
        PaymentTypeOne payment = new PaymentTypeOne(amount, debtorIban, creditorIban, details);
        return paymentRepository.save(payment);
    }

    private Payment saveTypeTwo(Double amount, String debtorIban, String creditorIban, String details) throws ConstraintViolationException {
        PaymentTypeTwo payment = new PaymentTypeTwo(amount, debtorIban, creditorIban, details);
        return paymentRepository.save(payment);
    }

    private Payment saveTypeThree(Currency currency, Double amount, String debtorIban, String creditorIban, String bicCode) throws ConstraintViolationException {
        PaymentTypeThree payment = new PaymentTypeThree(currency, amount, debtorIban, creditorIban, bicCode);
        return paymentRepository.save(payment);
    }
}
