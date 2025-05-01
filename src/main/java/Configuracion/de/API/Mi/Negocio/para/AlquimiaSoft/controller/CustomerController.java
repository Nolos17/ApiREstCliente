package Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft.controller;


import Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft.dto.CustomerUpdateDTO;
import Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft.dto.CustomerDTO;
import Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft.model.Customer;
import Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        if (customer.getAddresses() != null) {
            customer.getAddresses().forEach(address -> address.setCustomer(customer));
        }
        return customerService.saveCustomer(customer);
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers().stream()
                .map(customer -> {
                    CustomerDTO dto = new CustomerDTO();
                    dto.setId(customer.getId());
                    dto.setIdentificationType(customer.getIdentificationType());
                    dto.setIdentificationNumber(customer.getIdentificationNumber());
                    dto.setName(customer.getName());
                    dto.setEmail(customer.getEmail());
                    dto.setPhoneNumber(customer.getPhoneNumber());
                    dto.setMainProvince(customer.getMainProvince());
                    dto.setMainCity(customer.getMainCity());
                    dto.setMainAddress(customer.getMainAddress());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<CustomerDTO> searchCustomers(@RequestParam String query) {
        return customerService.getAllCustomers().stream()
                .filter(c -> c.getIdentificationNumber().toLowerCase().contains(query.toLowerCase()) ||
                        c.getName().toLowerCase().contains(query.toLowerCase()))
                .map(customer -> {
                    CustomerDTO dto = new CustomerDTO();
                    dto.setId(customer.getId());
                    dto.setIdentificationType(customer.getIdentificationType());
                    dto.setIdentificationNumber(customer.getIdentificationNumber());
                    dto.setName(customer.getName());
                    dto.setEmail(customer.getEmail());
                    dto.setPhoneNumber(customer.getPhoneNumber());
                    dto.setMainProvince(customer.getMainProvince());
                    dto.setMainCity(customer.getMainCity());
                    dto.setMainAddress(customer.getMainAddress());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getAllCustomers().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerUpdateDTO updatedCustomer) {
        Customer customer = customerService.getAllCustomers().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        customer.setIdentificationType(updatedCustomer.getIdentificationType());
        customer.setIdentificationNumber(updatedCustomer.getIdentificationNumber());
        customer.setName(updatedCustomer.getName());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setPhoneNumber(updatedCustomer.getPhoneNumber());

        return ResponseEntity.ok(customerService.saveCustomer(customer));
    }

    /*
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer updatedCustomer) {
        Customer customer = customerService.getAllCustomers().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        customer.setIdentificationType(updatedCustomer.getIdentificationType());
        customer.setIdentificationNumber(updatedCustomer.getIdentificationNumber());
        customer.setName(updatedCustomer.getName());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
        customer.setMainProvince(updatedCustomer.getMainProvince());
        customer.setMainCity(updatedCustomer.getMainCity());
        customer.setMainAddress(updatedCustomer.getMainAddress());
        customer.setAddresses(updatedCustomer.getAddresses());
        return ResponseEntity.ok(customerService.saveCustomer(customer));
    }
*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        Customer customer = customerService.getAllCustomers().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}