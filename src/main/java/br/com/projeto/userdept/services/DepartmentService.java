package br.com.projeto.userdept.services;

import br.com.projeto.userdept.controllers.dto.DepartmentDTO;
import br.com.projeto.userdept.entities.Department;
import br.com.projeto.userdept.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public List<DepartmentDTO> findAll() {
        return repository.findAll().stream().map(DepartmentDTO::from).toList();
    }

    public DepartmentDTO findById(Long id) {

        Optional<Department> existingDepartment = repository.findById(id);

        return existingDepartment.map(DepartmentDTO::from).orElse(null);
    }

    public DepartmentDTO save(DepartmentDTO departmentDTO) {
        Department department = DepartmentDTO.toEntity(departmentDTO);
        repository.save(department);
        return departmentDTO;
    }

    public DepartmentDTO update(Long id, DepartmentDTO department) {
        Optional<Department> existingDepartment = repository.findById(id);

        if (existingDepartment.isPresent()) {
            Department updatedDepartment = existingDepartment.get();
            updatedDepartment.setName(department.getName());

            Department savedDepartment = repository.save(updatedDepartment);

            return DepartmentDTO.from(savedDepartment);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
