package Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft.dto;

public class AddressDTO {
    private Long id;
    private String province;
    private String city;
    private String mainAddress;
    private String secondaryAddress;
    private Long customerId;

    // Getters y Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getMainAddress() { return mainAddress; }
    public void setMainAddress(String mainAddress) { this.mainAddress = mainAddress; }

    public String getSecondaryAddress() { return secondaryAddress; }
    public void setSecondaryAddress(String secondaryAddress) { this.secondaryAddress = secondaryAddress; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
}
