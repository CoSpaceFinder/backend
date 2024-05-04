package pwr.cospacefinderbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.cospacefinderbackend.dto.AddressDTO;
import pwr.cospacefinderbackend.exceptions.NotFoundException;
import pwr.cospacefinderbackend.model.Address;
import pwr.cospacefinderbackend.repository.AddressRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddress(Long id) {
        return addressRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Address with id " + id + " does not exist")
        );
    }

    public Address addAddress(AddressDTO address) {
        Address newAddress = new Address();
        newAddress.setCountry(address.getCountry());
        newAddress.setCity(address.getCity());
        newAddress.setAddress(address.getAddress());
        newAddress.setLatitude(address.getLatitude());
        newAddress.setLongitude(address.getLongitude());
        return addressRepository.save(newAddress);
    }

    public Address updateAddress(Long id, AddressDTO updatedAddress) {
        Address address = addressRepository.findById(id).orElse(null);
        if (address != null) {
            address.setCountry(updatedAddress.getCountry());
            address.setCity(updatedAddress.getCity());
            address.setAddress(updatedAddress.getAddress());
            address.setLatitude(updatedAddress.getLatitude());
            address.setLongitude(updatedAddress.getLongitude());
            return addressRepository.save(address);
        }
        throw new NotFoundException("Address with id " + id + " does not exist");
    }

    public Address deleteAddress(Long id) {
        Address address = addressRepository.findById(id).orElse(null);
        if (address != null) {
            addressRepository.delete(address);
            return address;
        } else {
            throw new NotFoundException("Address with id " + id + " does not exist");
        }
    }

}
