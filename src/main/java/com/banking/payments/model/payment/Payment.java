package com.banking.payments.model.payment;

import com.banking.payments.util.CurrencyUtil.Currency;
import com.banking.payments.enums.payment.PaymentStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "payments")
@DiscriminatorColumn(name="payment_type", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private Integer paymentId;
    @NotNull
    @Positive
    private Double amount;
    @NotNull
    private Currency currency;
    @Column(name = "debtor_iban")
    @NotNull
    private String debtorIban;
    @Column(name = "creditor_iban")
    @NotNull
    private String creditorIban;
    @Column(name = "creation_ts")
    @NotNull
    private Timestamp creationTs;
    @NotNull
    private PaymentStatus status;

    public Integer getPaymentId() {
        return this.paymentId;
    }

    protected void setAmount(Double amount) {
        this.amount = amount;
    }
    public Double getAmount() {
        return this.amount;
    }

    protected void setCurrency(Currency currency) {
        this.currency = currency;
    }
    public Currency getCurrency() {
        return this.currency;
    }

    protected void setDebtorIban(String debtorIban) {
        this.debtorIban = debtorIban;
    }
    public String getDebtorIban() {
        return this.debtorIban;
    }

    protected void setCreditorIban(String creditorIban) {
        this.creditorIban = creditorIban;
    }
    public String getCreditorIban() {
        return this.creditorIban;
    }

    private void setCreationTs(Timestamp creationTs) {
        this.creationTs = creationTs;
    }
    public Timestamp getCreationTs() {
        return this.creationTs;
    }

    private void setStatus(PaymentStatus status) {
        this.status = status;
    }
    public PaymentStatus getStatus() {
        return this.status;
    }

    protected Payment() {
        setCreationTs(new Timestamp(System.currentTimeMillis()));
        setStatus(PaymentStatus.PROCESSED);
    }

    public Integer cancelPayment() throws Exception {
        LocalDateTime localDateTime = getCreationTs().toLocalDateTime();
        if (localDateTime.isBefore(LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MAX))) {
            setStatus(PaymentStatus.CANCELLED);
            return getPaymentId();
        } else {
            throw new Exception(String.format("Invalid operation: Payment can only be cancelled on the same day. Payment created: %s. Current date: %s", getCreationTs(), Timestamp.valueOf(LocalDateTime.now())));
        }
    }
}
