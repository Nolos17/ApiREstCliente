package Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft.repository;

import Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft.model.Customer;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByIdentificationNumber(@NotBlank(message = "El número de identificación es obligatorio") String identificationNumber);
}
