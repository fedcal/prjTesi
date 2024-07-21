package com.filemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class FilemanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilemanagementApplication.class, args);
	}

}
