package com.botalimentazione;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@OpenAPIDefinition(
		info = @Info(
				title = "Applicativo Bot Alimentazione",
				version = "1.0.0",
				description = "Ms che contiene tutti i path per comunicare con il bot addestrato sull'alimentazione",
				contact = @Contact(
						name = "Federico Calò (matricola 678191)",
						email = "f.calo29@studenti.uniba.it",
						url = "https://www.federicocalo.dev"
				)
		),
		servers = {
				@Server(
						description = "Ambiente locale",
						url = "http://localhost:8084"
				)
		}
)
public class BotalimentazioneApplication {

	public static void main(String[] args) {
		SpringApplication.run(BotalimentazioneApplication.class, args);
	}

}
