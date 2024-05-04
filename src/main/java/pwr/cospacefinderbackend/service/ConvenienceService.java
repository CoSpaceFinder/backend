package pwr.cospacefinderbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.cospacefinderbackend.dto.ConvenienceDTO;
import pwr.cospacefinderbackend.exceptions.AlreadyExistsException;
import pwr.cospacefinderbackend.exceptions.NotFoundException;
import pwr.cospacefinderbackend.model.Convenience;
import pwr.cospacefinderbackend.repository.ConvenienceRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ConvenienceService {
    private final ConvenienceRepository convenienceRepository;

    public List<Convenience> getAllConveniences() {
        return convenienceRepository.findAll();
    }

    public Convenience getConvenience(Long id) {
        return convenienceRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Convenience with id " + id + " does not exist")
        );
    }

    public Convenience addConvenience(ConvenienceDTO Convenience) {
        if (convenienceRepository.existsByName(Convenience.getName())) {
            throw new AlreadyExistsException("Convenience with name " + Convenience.getName() + " already exists");
        }
        Convenience newConvenience = new Convenience();
        newConvenience.setName(Convenience.getName());
        return convenienceRepository.save(newConvenience);
    }

    public Convenience updateConvenience(Long id, ConvenienceDTO updatedConvenience) {
        Convenience Convenience = convenienceRepository.findById(id).orElse(null);
        if (Convenience != null) {
            if (convenienceRepository.existsByName(updatedConvenience.getName()) && !Convenience.getName().equals(updatedConvenience.getName())) {
                throw new AlreadyExistsException("Convenience with name " + updatedConvenience.getName() + " already exists");
            }
            Convenience.setName(updatedConvenience.getName());
            return convenienceRepository.save(Convenience);
        }
        throw new NotFoundException("Convenience with id " + id + " does not exist");
    }

    public Convenience deleteConvenience(Long id) {
        Convenience Convenience = convenienceRepository.findById(id).orElse(null);
        if (Convenience != null) {
            convenienceRepository.delete(Convenience);
            return Convenience;
        } else {
            throw new NotFoundException("Convenience with id " + id + " does not exist");
        }
    }
}
