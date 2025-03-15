package com.marcos.funcionario.service;

import com.marcos.funcionario.model.Employee;
import com.marcos.funcionario.model.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface IEmployerService {
    List<EmployeeDTO> getAllEmployees();
    Optional<EmployeeDTO> getEmployeeById(Long Id);
    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);
    void deleteEmployee(Long id);

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

}
