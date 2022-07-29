package com.banking.payments.controller;

import com.banking.payments.enums.payment.PaymentAction;
import com.banking.payments.service.LoggingService;
import com.banking.payments.service.PaymentService;
import com.banking.payments.util.CurrencyUtil.Currency;
import com.banking.payments.enums.payment.PaymentType;
import com.banking.payments.util.IpUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private LoggingService loggingService;

    @PostMapping("/create")
    public ResponseEntity<String> createPayment(@RequestParam(required = false) Currency currency,
                                                @RequestParam("paymentType") PaymentType paymentType,
                                                @RequestParam("amount") Double amount,
                                                @RequestParam("debtorIban") String debtorIban,
                                                @RequestParam("creditorIban") String creditorIban,
                                                @RequestParam(required = false) String details,
                                                @RequestParam(required = false) String bicCode,
                                                HttpServletRequest request) throws IOException {
        loggingService.saveLog(PaymentAction.CREATE, IpUtil.extractCountry(request.getRemoteAddr()));

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

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelPayment(@RequestParam Integer paymentId, HttpServletRequest request) throws IOException {
        loggingService.saveLog(PaymentAction.CANCEL, IpUtil.extractCountry(request.getRemoteAddr()));

        try {
            return ResponseEntity.status(HttpStatus.OK).body(String.format("Payment cancelled with ID: %d", paymentService.cancelPayment(paymentId)));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionUtils.getRootCauseMessage(ex));
        }
    }

    @GetMapping("/processed/desc")
    public ResponseEntity<String> getAllProcessedPaymentsDesc() {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.getAllProcessedPaymentsDesc().toString());
    }

    @GetMapping("/processed/asc")
    public ResponseEntity<String> getAllProcessedPaymentsAsc() {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.getAllProcessedPaymentsAsc().toString());
    }

    @GetMapping("/get")
    public ResponseEntity<String> getPayment(@RequestParam Integer paymentId) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(paymentService.getPayment(paymentId).toString());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionUtils.getRootCauseMessage(ex));
        }
    }
}
