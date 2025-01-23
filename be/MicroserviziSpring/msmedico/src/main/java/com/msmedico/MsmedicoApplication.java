package com.msmedico;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
@OpenAPIDefinition(
		info = @Info(
				title = "Applicativo Ms MEdico",
				version = "1.0.0",
				description = "Ms che contiene tutti i path per la gestione dei medici",
				contact = @Contact(
						name = "Federico Calò (matricola 678191)",
						email = "f.calo29@studenti.uniba.it",
						url = "https://www.federicocalo.dev"
				)
		),
		servers = {
				@Server(
						description = "Ambiente locale",
						url = "http://localhost:8087"
				)
		}
)
public class MsmedicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsmedicoApplication.class, args);
	}

}
