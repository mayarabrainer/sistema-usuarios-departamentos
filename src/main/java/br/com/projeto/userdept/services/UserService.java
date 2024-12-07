package br.com.projeto.userdept.services;

import br.com.projeto.userdept.controllers.dto.DepartmentDTO;
import br.com.projeto.userdept.controllers.dto.UserDTO;
import br.com.projeto.userdept.entities.Department;
import br.com.projeto.userdept.entities.User;
import br.com.projeto.userdept.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserDTO> findAll() {
        return repository.findAll().stream().map(UserDTO::from).toList();
    }

    public UserDTO findById(Long id) {

        Optional<User> existingUser = repository.findById(id);

        return existingUser.map(UserDTO::from).orElse(null);
    }

    public UserDTO save(UserDTO userDTO) {
        User user = UserDTO.toEntity(userDTO);
        repository.save(user);
        return userDTO;
    }

    public UserDTO update(Long id, UserDTO user) {
        Optional<User> existingUser = repository.findById(id);

        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();

            updatedUser.setName(user.getNome());
            updatedUser.setEmail(user.getEmail());
            User savedUser = repository.save(updatedUser);
            return UserDTO.from(savedUser);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
