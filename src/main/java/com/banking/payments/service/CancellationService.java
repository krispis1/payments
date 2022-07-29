package com.banking.payments.service;

import com.banking.payments.enums.payment.PaymentType;
import com.banking.payments.model.payment.cancellation.Cancellation;
import com.banking.payments.repository.CancellationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Service
public class CancellationService {
    @Autowired
    private CancellationRepository cancellationRepository;

    @Transactional(rollbackOn = Exception.class)
    public void saveCancellation(PaymentType paymentType, Integer paymentId, Timestamp creationTs) throws Exception {
        cancellationRepository.save(new Cancellation(paymentType, paymentId, calculateFee(paymentType, creationTs), Timestamp.from(Instant.now())));
    }

    public Double calculateFee(PaymentType paymentType, Timestamp creationTs) throws Exception {
        double k;
        long hours = TimeUnit.MILLISECONDS.toHours(Timestamp.from(Instant.now()).getTime() - creationTs.getTime());

        switch (paymentType) {
            case ONE:
                k = 0.05;
                break;
            case TWO:
                k = 0.1;
                break;
            case THREE:
                k = 0.15;
                break;
            default:
                throw new Exception("Incorrect payment type");
        }

        return k * hours;
    }
}
