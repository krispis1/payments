package com.banking.payments.model.payment.cancellation;

import com.banking.payments.enums.payment.PaymentType;
import com.banking.payments.util.CurrencyUtil.Currency;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "cancellations")
public class Cancellation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "cancellations", sequenceName = "cancellations")
    @Column(name = "cancellation_id")
    private Integer cancellationId;
    @Column(name = "payment_type")
    @NotNull
    private final PaymentType paymentType;
    @Column(name = "payment_id")
    @NotNull
    private final Integer paymentId;
    @Column(name = "cancellation_fee")
    @NotNull
    private final Double cancellationFee;
    @NotNull
    private static final Currency currency = Currency.EUR;
    @Column(name = "creation_ts")
    @NotNull
    private final Timestamp creationTs;

    public Integer getCancellationId() {
        return this.cancellationId;
    }

    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    public Integer getPaymentId() {
        return this.paymentId;
    }

    public Double getCancellationFee() {
        return this.cancellationFee;
    }

    public Timestamp getCreationTs() {
        return this.creationTs;
    }

    public Cancellation(PaymentType paymentType, Integer paymentId, Double cancellationFee, Timestamp creationTs) {
        this.paymentId = paymentId;
        this.paymentType = paymentType;
        this.cancellationFee = cancellationFee;
        this.creationTs = creationTs;
    }
}
