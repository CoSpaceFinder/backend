package pwr.cospacefinderbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.cospacefinderbackend.dto.UserDTO;
import pwr.cospacefinderbackend.model.Role;
import pwr.cospacefinderbackend.model.User;
import pwr.cospacefinderbackend.exceptions.AlreadyExistsException;
import pwr.cospacefinderbackend.exceptions.NotFoundException;
import pwr.cospacefinderbackend.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User with id " + id + " does not exist")
        );
    }

    public User getUserByMail(String mail) {
        return userRepository.findByMail(mail).orElseThrow(
                () -> new NotFoundException("User with mail " + mail + " does not exist")
        );
    }

    public User addUser(UserDTO user) {
        if (userRepository.existsByMail(user.getMail())) {
            throw new AlreadyExistsException("User with mail " + user.getMail() + " already exists");
        }
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setMail(user.getMail());
        Role role = roleService.getRole(user.getRoleId());
        newUser.setRole(role);
        return userRepository.save(newUser);
    }

    public User updateUser(Long id, UserDTO updatedUser) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (userRepository.existsByMail(updatedUser.getMail()) && !user.getMail().equals(updatedUser.getMail())) {
                throw new AlreadyExistsException("User with mail " + updatedUser.getMail() + " already exists");
            }
            user.setName(updatedUser.getName());
            user.setSurname(updatedUser.getSurname());
            user.setMail(updatedUser.getMail());
            Role role = roleService.getRole(updatedUser.getRoleId());
            user.setRole(role);
            return userRepository.save(user);
        }
        throw new NotFoundException("User with id " + id + " does not exist");
    }

    public User deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
            return user;
        } else {
            throw new NotFoundException("User with id " + id + " does not exist");
        }
    }
}
