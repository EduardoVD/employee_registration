package br.edu.famper.employeeregistration.repository;

import br.edu.famper.employeeregistration.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {}
