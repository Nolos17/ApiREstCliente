package Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @NotBlank(message = "La provincia matriz es obligatoria")
    private String mainProvince;

    @NotBlank(message = "La ciudad matriz es obligatoria")
    private String mainCity;

    @NotBlank(message = "La dirección matriz es obligatoria")
    private String mainAddress;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Address> addresses;
}
