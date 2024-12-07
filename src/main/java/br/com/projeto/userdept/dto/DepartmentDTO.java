package br.com.projeto.userdept.controllers.dto;

import br.com.projeto.userdept.entities.Department;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DepartmentDTO {

    private Long id;

    @NotNull
    @NotBlank(message = "O nome do departamento não pode ser vazio ou em branco.")
    private String name;

    public static DepartmentDTO from(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(department.getId());
        departmentDTO.setName(department.getName());
        return departmentDTO;
    }

    public static Department toEntity(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setName(departmentDTO.getName());
        return department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
