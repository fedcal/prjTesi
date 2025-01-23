package com.msinfermiere;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
@OpenAPIDefinition(
		info = @Info(
				title = "Applicativo Ms Infermiere",
				version = "1.0.0",
				description = "Ms che contiene tutti i path per la gestione degli infermieri",
				contact = @Contact(
						name = "Federico Calò (matricola 678191)",
						email = "f.calo29@studenti.uniba.it",
						url = "https://www.federicocalo.dev"
				)
		),
		servers = {
				@Server(
						description = "Ambiente locale",
						url = "http://localhost:8086"
				)
		}
)
public class MsinfermiereApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsinfermiereApplication.class, args);
	}

}
