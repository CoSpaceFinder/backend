package pwr.cospacefinderbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.cospacefinderbackend.repository.AddressRepository;

@Service
@AllArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
}
