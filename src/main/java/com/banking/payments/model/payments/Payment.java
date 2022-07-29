package com.banking.payments.model.payments;

import com.banking.payments.util.CurrencyUtil.Currency;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "payments")
@DiscriminatorColumn(name="payment_type", discriminatorType = DiscriminatorType.INTEGER)
public class Payment {
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

    protected Payment() {
        setCreationTs(new Timestamp(System.currentTimeMillis()));
    }
}
