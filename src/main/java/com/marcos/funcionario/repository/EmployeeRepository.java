package com.marcos.funcionario.repository;

import com.marcos.funcionario.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides all the necessaty CRUD operations like save(), findAll(),  and deleteById() without needing to wirite
 * them tourself
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
