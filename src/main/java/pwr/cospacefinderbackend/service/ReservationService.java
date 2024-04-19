package pwr.cospacefinderbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.cospacefinderbackend.repository.ReservationRepository;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;


}
