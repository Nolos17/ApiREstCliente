package Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerUpdateDTO {
    @NotBlank(message = "El tipo de identificación es obligatorio")
    private String identificationType;

    @NotBlank(message = "El número de identificación es obligatorio")
    private String identificationNumber;

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotBlank(message = "El número de teléfono es obligatorio")
    private String phoneNumber;
}
