package pwr.cospacefinderbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.cospacefinderbackend.dto.AvailabilityDTO;
import pwr.cospacefinderbackend.exceptions.AlreadyExistsException;
import pwr.cospacefinderbackend.exceptions.NotFoundException;
import pwr.cospacefinderbackend.model.Availability;
import pwr.cospacefinderbackend.model.Space;
import pwr.cospacefinderbackend.repository.AvailabilityRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AvailabilityService {
    private final AvailabilityRepository availabilityRepository;
    private final SpaceService spaceService;

    public List<Availability> getAllAvailabilities() {
        return availabilityRepository.findAll();
    }

    public Availability getAvailability(Long id) {
        return availabilityRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Availability with id " + id + " does not exist")
        );
    }

    public Availability addAvailability(AvailabilityDTO availability, Long spaceId) {
        Space space = spaceService.getSpace(spaceId);
        if (availabilityRepository.existsBySpaceAndDayOfWeek(space, availability.getDayOfWeek())) {
            throw new AlreadyExistsException("Availability with space " + space.getName() +
                    " and day of week " + availability.getDayOfWeek() + " already exists");
        }
        Availability newAvailability = new Availability();
        newAvailability.setSpace(space);
        newAvailability.setDayOfWeek(availability.getDayOfWeek());
        newAvailability.setStartTime(availability.getStartTime());
        newAvailability.setEndTime(availability.getEndTime());
        return availabilityRepository.save(newAvailability);
    }

    public Availability updateAvailability(Long id, AvailabilityDTO updatedAvailability, Long spaceId) {
        Availability availability = availabilityRepository.findById(id).orElse(null);
        Space space = spaceService.getSpace(spaceId);
        if (availability != null) {
            if (availabilityRepository.existsBySpaceAndDayOfWeek(space, updatedAvailability.getDayOfWeek()) &&
                    (!availability.getSpace().equals(space) ||
                    !availability.getDayOfWeek().equals(updatedAvailability.getDayOfWeek()))) {
                throw new AlreadyExistsException("Availability with space " + space.getName() +
                        " and day of week " + updatedAvailability.getDayOfWeek() + " already exists");
            }
            availability.setSpace(space);
            availability.setDayOfWeek(updatedAvailability.getDayOfWeek());
            availability.setStartTime(updatedAvailability.getStartTime());
            availability.setEndTime(updatedAvailability.getEndTime());
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
