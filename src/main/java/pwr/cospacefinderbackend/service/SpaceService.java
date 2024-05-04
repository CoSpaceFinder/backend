package pwr.cospacefinderbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.cospacefinderbackend.dto.SpaceDTO;
import pwr.cospacefinderbackend.exceptions.AlreadyExistsException;
import pwr.cospacefinderbackend.exceptions.NotFoundException;
import pwr.cospacefinderbackend.model.Space;
import pwr.cospacefinderbackend.repository.SpaceRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SpaceService {
    private final SpaceRepository spaceRepository;

    public List<Space> getAllSpaces() {
        return spaceRepository.findAll();
    }

    public Space getSpace(Long id) {
        return spaceRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Space with id " + id + " does not exist")
        );
    }

    public Space addSpace(SpaceDTO space) {
        if (spaceRepository.existsByName(space.getName())) {
            throw new AlreadyExistsException("Space with name " + space.getName() + " already exists");
        }
        Space newspace = new Space();
        newspace.setName(space.getName());
        return spaceRepository.save(newspace);
    }

    public Space updateSpace(Long id, SpaceDTO updatedSpace) {
        Space space = spaceRepository.findById(id).orElse(null);
        if (space != null) {
            if (spaceRepository.existsByName(updatedSpace.getName()) && !space.getName().equals(updatedSpace.getName())) {
                throw new AlreadyExistsException("Space with name " + updatedSpace.getName() + " already exists");
            }
            space.setName(updatedSpace.getName());
            return spaceRepository.save(space);
        }
        throw new NotFoundException("Space with id " + id + " does not exist");
    }

    public Space deleteSpace(Long id) {
        Space space = spaceRepository.findById(id).orElse(null);
        if (space != null) {
            spaceRepository.delete(space);
            return space;
        } else {
            throw new NotFoundException("Space with id " + id + " does not exist");
        }
    }

}
