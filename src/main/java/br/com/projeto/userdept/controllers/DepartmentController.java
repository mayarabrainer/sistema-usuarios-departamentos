package br.com.projeto.userdept.controllers;

import br.com.projeto.userdept.controllers.dto.DepartmentDTO;
import br.com.projeto.userdept.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<DepartmentDTO> findAll() {
        return departmentService.findAll();
    }

    @GetMapping(value = "/{id}")
    public DepartmentDTO findById(@PathVariable Long id) {
        return departmentService.findById(id);
    }

    @PostMapping
    public DepartmentDTO insert(@RequestBody DepartmentDTO department) {
        return departmentService.save(department);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DepartmentDTO> update(@PathVariable Long id, @RequestBody DepartmentDTO department) {
        DepartmentDTO departmentDTO = departmentService.update(id, department);

        return ResponseEntity.ok(departmentDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        departmentService.delete(id);
    }


}
