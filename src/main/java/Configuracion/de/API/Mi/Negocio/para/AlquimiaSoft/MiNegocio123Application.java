package Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft.repository")
@ComponentScan(basePackages = "Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft")
public class MiNegocio123Application {

	public static void main(String[] args) {
		SpringApplication.run(MiNegocio123Application.class, args);
	}

}
