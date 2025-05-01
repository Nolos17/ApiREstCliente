package Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft.controller;

import Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft.dto.AddressDTO;
import Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft.model.Address;
import Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft.model.Customer;
import Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft.service.AddressService;
import Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;




@RestController
@RequestMapping("/api/addresses")

public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Address createAddress(@RequestBody Address address) {
        return addressService.saveAddress(address);
    }

    @GetMapping
    public List<AddressDTO> getAllAddresses() {
        return addressService.getAllAddresses().stream()
                .map(address -> {
                    AddressDTO dto = new AddressDTO();
                    dto.setId(address.getId());
                    dto.setProvince(address.getProvince());
                    dto.setCity(address.getCity());
                    dto.setMainAddress(address.getMainAddress());
                    dto.setSecondaryAddress(address.getSecondaryAddress());
                    dto.setCustomerId(address.getCustomer() != null ? address.getCustomer().getId() : null);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
        Address address = addressService.getAllAddresses().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (address == null) {
            return ResponseEntity.notFound().build();
        }
        AddressDTO dto = new AddressDTO();
        dto.setId(address.getId());
        dto.setProvince(address.getProvince());
        dto.setCity(address.getCity());
        dto.setMainAddress(address.getMainAddress());
        dto.setSecondaryAddress(address.getSecondaryAddress());
        dto.setCustomerId(address.getCustomer() != null ? address.getCustomer().getId() : null);
        return ResponseEntity.ok(dto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address updatedAddress) {
        Address address = addressService.getAllAddresses().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (address == null) {
            return ResponseEntity.notFound().build();
        }
        address.setProvince(updatedAddress.getProvince());
        address.setCity(updatedAddress.getCity());
        address.setMainAddress(updatedAddress.getMainAddress());
        address.setSecondaryAddress(updatedAddress.getSecondaryAddress());
        return ResponseEntity.ok(addressService.saveAddress(address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        Address address = addressService.getAllAddresses().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (address == null) {
            return ResponseEntity.notFound().build();
        }
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-customer/{customerId}")
    public ResponseEntity<List<AddressDTO>> getAddressesByCustomerId(@PathVariable Long customerId) {
        // Obtener el cliente
        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        List<AddressDTO> addresses = new ArrayList<>();

        // Agregar dirección matriz (desde el Customer)
        AddressDTO mainAddress = new AddressDTO();
        mainAddress.setProvince(customer.getMainProvince());
        mainAddress.setCity(customer.getMainCity());
        mainAddress.setMainAddress(customer.getMainAddress());
        mainAddress.setSecondaryAddress(null); // O alguna representación vacía
        mainAddress.setCustomerId(customerId);
        addresses.add(mainAddress);

        // Agregar direcciones adicionales (desde Address)
        List<AddressDTO> additionalAddresses = addressService.getAllAddresses().stream()
                .filter(address -> address.getCustomer() != null && address.getCustomer().getId().equals(customerId))
                .map(address -> {
                    AddressDTO dto = new AddressDTO();
                    dto.setId(address.getId());
                    dto.setProvince(address.getProvince());
                    dto.setCity(address.getCity());
                    dto.setMainAddress(address.getMainAddress());
                    dto.setSecondaryAddress(address.getSecondaryAddress());
                    dto.setCustomerId(customerId);
                    return dto;
                })
                .collect(Collectors.toList());

        addresses.addAll(additionalAddresses);

        return ResponseEntity.ok(addresses);
    }


}
