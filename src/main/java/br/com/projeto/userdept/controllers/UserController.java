package br.com.projeto.userdept.controllers;

import br.com.projeto.userdept.dto.UserDTO;
import br.com.projeto.userdept.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Operation(summary = "Busca todos os usuários", description = "Retorna uma lista com todos os usuários cadastrados")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    @GetMapping
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @Operation(summary = "Busca um usuário por ID", description = "Retorna os dados de um usuário específico com base no ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping(value = "/{id}")
    public UserDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @Operation(summary = "Insere um novo usuário", description = "Adiciona um novo usuário à base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados")
    })
    @PostMapping
    public UserDTO insert(@RequestBody @Valid UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @Operation(summary = "Atualiza um usuário", description = "Atualiza os dados de um usuário existente com base no ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody @Valid UserDTO user) {
        log.info("[UserController] - /update user id {} with name {}", id, user.getNome());
        UserDTO userDTO = userService.update(id, user);
        return ResponseEntity.ok(userDTO);
    }

    @Operation(summary = "Exclui um usuário", description = "Remove um usuário da base de dados com base no ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
