package pe.edu.upc.patitasolidaria.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = "pe.edu.upc.patitasolidaria.backend")
@EnableJpaAuditing
public class PatitaSolidariaBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(PatitaSolidariaBackendApplication.class, args);
	}
}