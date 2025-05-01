package Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft.service;

import Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft.model.Address;
import Configuracion.de.API.Mi.Negocio.para.AlquimiaSoft.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
    public List<Address> getAddressesByCustomerId(Long customerId){
        return addressRepository.findAll();
    }



}
