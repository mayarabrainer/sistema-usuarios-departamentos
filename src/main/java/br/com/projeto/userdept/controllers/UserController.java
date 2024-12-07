package br.com.projeto.userdept.controllers;

import br.com.projeto.userdept.controllers.dto.DepartmentDTO;
import br.com.projeto.userdept.controllers.dto.UserDTO;
import br.com.projeto.userdept.entities.User;
import br.com.projeto.userdept.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/{id}")
    public UserDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public UserDTO insert(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody  UserDTO user) {
        UserDTO userDTO = userService.update(id, user);

        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }



}