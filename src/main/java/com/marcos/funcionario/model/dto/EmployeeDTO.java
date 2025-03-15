package com.marcos.funcionario.model.dto;

/**
 * Java Record: A special kind of class in Java that reduces boilerplate code by automatically  generating, constructors, getters, toString(),
 * equals and hashCode() methods. it's ideal for DTOs that carry data between the server and client
 */
public record EmployeeDTO(Long id, String firstName, String lastName, String email) {
}
