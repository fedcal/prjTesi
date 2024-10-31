package com.botai;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@OpenAPIDefinition(
		info = @Info(
				title = "Bot Offerte Bandi",
				version = "1.0.0",
				description = "Microservizio dedicato alla gestione della chat relativa al bot sull'intelligenza artificiale "
						+ "dei bot",
				contact = @Contact(
						name = "Federico Calò (matricola 678191)",
						email = "f.calo29@studenti.uniba.it",
						url = "https://www.federicocalo.dev"
				)
		),
		servers = {
				@Server(
						description = "Ambiente locale",
						url = "http://localhost:8083"
				)
		}
)
public class BotaiApplication {
	public static void main(String[] args) {
		SpringApplication.run(BotaiApplication.class, args);
	}
}
