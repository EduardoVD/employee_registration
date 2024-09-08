package br.edu.famper.employeeregistration.controller;

import br.edu.famper.employeeregistration.model.Employee;
import br.edu.famper.employeeregistration.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.findById(id);

        if (employee.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(employee);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employee With ID " + id + " Not Found...");
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Employee employee) {

        if (employee.getName() == null || employee.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Employee 'Name' Cannot Be Empty...");
        } else if (employee.getRole() == null || employee.getRole().isEmpty() ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Employee 'Role' Cannot Be Empty...");
        } else if (employee.getDepartment() == null || employee.getDepartment().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Employee 'Department' Cannot Be Empty...");
        } else if (employee.getAdmissionDate() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Employee 'Admission Date' Cannot Be Empty...");
        } else { Employee savedEmployee = employeeService.save(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Employee employee) {
        Optional<Employee> existingEmployee = employeeService.findById(id);

        if (existingEmployee.isPresent()) {
            Employee employeeToUpdate = existingEmployee.get();
            employeeToUpdate.setName(employee.getName() != null ? employee.getName() : employeeToUpdate.getName());
            employeeToUpdate.setRole(employee.getRole() != null ? employee.getRole() : employeeToUpdate.getRole());
            employeeToUpdate.setDepartment(employee.getDepartment() != null ? employee.getDepartment() : employeeToUpdate.getDepartment());
            employeeToUpdate.setAdmissionDate(employee.getAdmissionDate() != null ? employee.getAdmissionDate() : employeeToUpdate.getAdmissionDate());
            employeeToUpdate.setEmail(employee.getEmail() != null ? employee.getEmail() : employeeToUpdate.getEmail());
            employeeToUpdate.setPhone(employee.getPhone() != null ? employee.getPhone() : employeeToUpdate.getPhone());

            Employee updatedEmployee = employeeService.update(employeeToUpdate);
            return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employee With ID " + id + " Not Found...");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.findById(id);

        if (employee.isPresent()) {
            employeeService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employee With ID " + id + " Not Found...");
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        employeeService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("All Employee's Deleted...");
    }
}
