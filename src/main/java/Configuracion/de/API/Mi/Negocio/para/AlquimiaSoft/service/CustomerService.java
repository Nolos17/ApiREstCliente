package Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft.service;

import Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft.model.Customer;
import Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        Optional<Customer> existing = customerRepository.findByIdentificationNumber(customer.getIdentificationNumber());

        // Verifica si ya existe otro cliente con el mismo número de identificación
        if (existing.isPresent() && !existing.get().getId().equals(customer.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un cliente con ese número de identificación.");
        }

        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }



    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
