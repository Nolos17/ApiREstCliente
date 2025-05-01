package Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String identificationType;
    private String identificationNumber;
    private String name;
    private String email;
    private String phoneNumber;
    private String mainProvince;
    private String mainCity;
    private String mainAddress;
}