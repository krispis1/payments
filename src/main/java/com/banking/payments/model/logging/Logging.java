package com.banking.payments.model.logging;

import com.banking.payments.enums.payment.PaymentAction;
import jdk.internal.net.http.common.Log;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "logging")
public class Logging {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "logging", sequenceName = "logging")
    @Column(name = "logging_id")
    private Integer loggingId;
    private final String clientCountry;
    @Column(name = "payment_action")
    @NotNull
    private final PaymentAction paymentAction;
    @NotNull
    private final Timestamp ts;

    public Logging(String clientCountry, PaymentAction paymentAction, Timestamp ts) {
        this.clientCountry = clientCountry;
        this.paymentAction = paymentAction;
        this.ts = ts;
    }

    public String getClientCountry() {
        return this.clientCountry;
    }

    public PaymentAction getPaymentAction() {
        return this.paymentAction;
    }

    public Timestamp getTs() {
        return this.ts;
    }
}
