package com.banking.payments.controller;

import com.banking.payments.service.PaymentService;
import com.banking.payments.util.CurrencyUtil.Currency;
import com.banking.payments.util.PaymentType;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<String> createPayment(@RequestParam(required = false) Currency currency,
                                                @RequestParam("paymentType") PaymentType paymentType,
                                                @RequestParam("amount") Double amount,
                                                @RequestParam("debtorIban") String debtorIban,
                                                @RequestParam("creditorIban") String creditorIban,
                                                @RequestParam(required = false) String details,
                                                @RequestParam(required = false) String bicCode) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(paymentService.savePayment(currency,
                                                                                        paymentType,
                                                                                        amount,
                                                                                        debtorIban,
                                                                                        creditorIban,
                                                                                        details,
                                                                                        bicCode)
                                                                                        .getPaymentId().toString());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionUtils.getRootCauseMessage(ex));
        }
    }
}
