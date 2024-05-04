package pwr.cospacefinderbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.cospacefinderbackend.dto.AvailabilityDTO;
import pwr.cospacefinderbackend.exceptions.NotFoundException;
import pwr.cospacefinderbackend.model.Availability;
import pwr.cospacefinderbackend.repository.AvailabilityRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AvailabilityService {
    private final AvailabilityRepository availabilityRepository;

    public List<Availability> getAllAvailabilities() {
        return availabilityRepository.findAll();
    }

    public Availability getAvailability(Long id) {
        return availabilityRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Availability with id " + id + " does not exist")
        );
    }

    public Availability addAvailability(AvailabilityDTO availability) {
        Availability newAvailability = new Availability();
        newAvailability.setDayOfWeek(availability.getDayOfWeek());
        newAvailability.setStartTime(availability.getStartTime());
        newAvailability.setEndTime(availability.getEndTime());
        newAvailability.setOpen(availability.isOpen());
        return availabilityRepository.save(newAvailability);
    }

    public Availability updateAvailability(Long id, AvailabilityDTO updatedAvailability) {
        Availability availability = availabilityRepository.findById(id).orElse(null);
        if (availability != null) {
            availability.setDayOfWeek(updatedAvailability.getDayOfWeek());
            availability.setStartTime(updatedAvailability.getStartTime());
            availability.setEndTime(updatedAvailability.getEndTime());
            availability.setOpen(updatedAvailability.isOpen());
            return availabilityRepository.save(availability);
        }
        throw new NotFoundException("Availability with id " + id + " does not exist");
    }

    public Availability deleteAvailability(Long id) {
        Availability availability = availabilityRepository.findById(id).orElse(null);
        if (availability != null) {
            availabilityRepository.delete(availability);
            return availability;
        } else {
            throw new NotFoundException("Availability with id " + id + " does not exist");
        }
    }

}
