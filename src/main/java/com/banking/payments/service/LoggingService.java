package com.banking.payments.service;

import com.banking.payments.enums.payment.PaymentAction;
import com.banking.payments.model.logging.Logging;
import com.banking.payments.repository.LoggingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.sql.Timestamp;
import java.time.Instant;

@Service
public class LoggingService {
    @Autowired
    private LoggingRepository loggingRepository;

    public void saveLog(PaymentAction paymentAction, String clientCountry) throws ConstraintViolationException {
        loggingRepository.save(new Logging(clientCountry, paymentAction, Timestamp.from(Instant.now())));
    }
}
