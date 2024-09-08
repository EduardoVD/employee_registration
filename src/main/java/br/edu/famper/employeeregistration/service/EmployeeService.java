package br.edu.famper.employeeregistration.service;

import br.edu.famper.employeeregistration.model.Employee;
import br.edu.famper.employeeregistration.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {return employeeRepository.save(employee);}

    public List<Employee> findAll() {return employeeRepository.findAll();}

    public Optional<Employee> findById(Long id) {return employeeRepository.findById(id);}

    public Employee update(Employee employee) {return employeeRepository.save(employee);}

    public void deleteById(Long id) {employeeRepository.deleteById(id);}

    public void deleteAll() {employeeRepository.deleteAll();}
}
