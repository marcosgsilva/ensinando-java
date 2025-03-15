package com.marcos.funcionario.service.impl;

import com.marcos.funcionario.model.Employee;
import com.marcos.funcionario.model.dto.EmployeeDTO;
import com.marcos.funcionario.repository.EmployeeRepository;
import com.marcos.funcionario.service.IEmployerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements IEmployerService {

    /**
     * In the service implementation, add the conversion logic between Employee (entity) and EmployeeDTO (DTO):
     * The conversion logic (between Entity and DTO) is kept in the service layer to keep the controller clean and focused only on handling HTTP requests.
     * The convertToDTO() method converts an Employee entity to EmployeeDTO, while convertToEntity() does the reverse.
     */
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private EmployeeDTO convertToDTO(Employee employee){
        return new EmployeeDTO(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail());

    }

    @Override
    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.setFirstName(employeeDTO.firstName());
        employee.setLastName(employeeDTO.lastName());
        employee.setEmail(employeeDTO.email());
        Employee updateEmployee = employeeRepository.save(employee);
        return convertToDTO(updateEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO){
        Employee employee = convertToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO((savedEmployee));
    }

    private Employee convertToEntity(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.firstName());
        employee.setLastName(employeeDTO.lastName());
        employee.setEmail(employeeDTO.email());
        return employee;
    }
}
