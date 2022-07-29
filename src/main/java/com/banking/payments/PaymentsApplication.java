package com.banking.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class PaymentsApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(PaymentsApplication.class, args);
	}
}
