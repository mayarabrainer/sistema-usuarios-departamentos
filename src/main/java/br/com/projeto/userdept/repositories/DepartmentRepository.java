package br.com.projeto.userdept.repositories;

import br.com.projeto.userdept.entities.Department;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
