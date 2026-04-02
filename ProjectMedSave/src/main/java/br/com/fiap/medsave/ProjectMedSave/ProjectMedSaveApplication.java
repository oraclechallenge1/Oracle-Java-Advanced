package br.com.fiap.medsave.ProjectMedSave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
public class ProjectMedSaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectMedSaveApplication.class, args);
	}

}
