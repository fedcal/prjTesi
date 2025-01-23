package com.mspazienti;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
@OpenAPIDefinition(
		info = @Info(
				title = "Applicativo Ms Pazienti",
				version = "1.0.0",
				description = "Ms che contiene tutti i path per la gestione dei pazienti",
				contact = @Contact(
						name = "Federico Calò (matricola 678191)",
						email = "f.calo29@studenti.uniba.it",
						url = "https://www.federicocalo.dev"
				)
		),
		servers = {
				@Server(
						description = "Ambiente locale",
						url = "http://localhost:8085"
				)
		}
)
public class MspazientiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MspazientiApplication.class, args);
	}

}
