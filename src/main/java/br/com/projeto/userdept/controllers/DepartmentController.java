package br.com.projeto.userdept.controllers;

import br.com.projeto.userdept.dto.DepartmentDTO;
import br.com.projeto.userdept.services.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Operation(summary = "Busca todos os departamentos", description = "Retorna uma lista com todos os departamentos cadastrados")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    @GetMapping
    public List<DepartmentDTO> findAll() {
        return departmentService.findAll();
    }

    @Operation(summary = "Busca um departamento por ID", description = "Retorna os dados de um departamento específico com base no ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida"),
            @ApiResponse(responseCode = "404", description = "Departamento não encontrado")
    })
    @GetMapping(value = "/{id}")
    public DepartmentDTO findById(@PathVariable Long id) {
        return departmentService.findById(id);
    }

    @Operation(summary = "Insere um novo departamento", description = "Adiciona um novo departamento à base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Departamento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados")
    })
    @PostMapping
    public DepartmentDTO insert(@RequestBody @Valid DepartmentDTO department) {
        return departmentService.save(department);
    }

    @Operation(summary = "Atualiza um departamento", description = "Atualiza os dados de um departamento existente com base no ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Departamento atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Departamento não encontrado"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<DepartmentDTO> update(@PathVariable Long id, @RequestBody DepartmentDTO department) {
        DepartmentDTO departmentDTO = departmentService.update(id, department);
        return ResponseEntity.ok(departmentDTO);
    }

    @Operation(summary = "Exclui um departamento", description = "Remove um departamento da base de dados com base no ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Departamento excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Departamento não encontrado")
    })
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        departmentService.delete(id);
    }
}
