package com.marcos.funcionario.service.impl;

import com.marcos.funcionario.model.Employee;
import com.marcos.funcionario.model.dto.EmployeeDTO;
import com.marcos.funcionario.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployerServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void testMustReturnEmployeeWhenIsValid() {
        Employee emp1 = new Employee();
        emp1.setId(1L);
        emp1.setFirstName("Alice");
        emp1.setLastName("Smith");
        emp1.setEmail("Alice@email.com");

        Employee emp2 = new Employee();
        emp2.setFirstName("Bob");
        emp2.setLastName("Johnson");
        emp2.setEmail("bob@email.com");

        List<Employee> employees = Arrays.asList(emp1, emp2);

        when(employeeRepository.findAll()).thenReturn(employees);

        List<EmployeeDTO> result = employeeService.getAllEmployees();

        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).firstName());
        assertEquals("Johnson", result.get(1).lastName());

        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testReturnEmployeeByIdReturnIsValid() {
        Employee emp1 = new Employee();
        emp1.setId(1L);
        emp1.setFirstName("Marcos");
        emp1.setLastName("Silva");
        emp1.setEmail("marcos@gmail.com");

        Employee employee = emp1;
        Long id = 1L;
        when(employeeRepository.findById(id)).thenReturn(Optional.of(emp1));

        Optional<EmployeeDTO> result = employeeService.getEmployeeById(1L);
        assertEquals("Marcos", result.get().firstName());
        //verifica se o valor não está vazio
        assertTrue(result.isPresent());

        verify(employeeRepository, times(1)).findById(id);
    }

    @Test
    void testValidDeletedEmployee() {
        Long employeeId = 1L;
        doNothing().when(employeeRepository).deleteById(employeeId);

        employeeService.deleteEmployee((employeeId));
        verify(employeeRepository, times(1)).deleteById(employeeId);

    }

    @Test
    void TestReturnValidUpdate(){
        Long id = 1L;
        EmployeeDTO employeeDTO = new EmployeeDTO(id,"John","Doe", "marcos.silva@gmail.com");
        Employee employeeExists = new Employee();
        employeeExists.setId(1L);
        employeeExists.setFirstName("Marcos");
        employeeExists.setLastName("Silva");
        employeeExists.setEmail("marcos@gmail.com");

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employeeExists));

        when(employeeRepository.save(any(Employee.class))).thenAnswer(invocation->invocation.getArgument(0));

        EmployeeDTO updatedemployeeDTO = employeeService.updateEmployee(id, employeeDTO);

        // Verifica se os métodos foram chamados corretamente
        verify(employeeRepository, times(1)).findById(id);
        verify(employeeRepository, times(1)).save(any(Employee.class));

        //verifica se o retorno foi esperado
        assertNotNull(updatedemployeeDTO);
        assertEquals("John", updatedemployeeDTO.firstName());
        assertEquals("Doe", updatedemployeeDTO.lastName());
        assertEquals("marcos.silva@gmail.com", updatedemployeeDTO.email());
    }

    @Test
    void validSaveEmployee(){
        EmployeeDTO employeeDTO = new EmployeeDTO(1L, "Marcos", "Paulo", "marcos@gmail.com");
        Employee employeeExists = new Employee();
        employeeExists.setId(1L);
        employeeExists.setFirstName("Marcos");
        employeeExists.setLastName("Silva");
        employeeExists.setEmail("marcos@gmail.com");
        when(employeeRepository.save(any(Employee.class))).thenReturn(employeeExists);

        EmployeeDTO result = employeeService.saveEmployee(employeeDTO);

        assertNotNull(result);
        assertEquals("Marcos", result.firstName());
        assertEquals("Silva", result.lastName());
        verify(employeeRepository, times(1)).save(any(Employee.class));


    }


}
