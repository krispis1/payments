package com.banking.payments;

import com.banking.payments.model.payments.types.PaymentTypeOne;
import com.banking.payments.service.PaymentService;
import com.banking.payments.util.CurrencyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class PaymentsApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(PaymentsApplication.class, args);
	}
}
