package com.marcos.funcionario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Essa classe é uma entidade que representa a tabela do Banco de dados
 * Lembre getter e setter são a forma como os dados serão acessados por outras classes, não tem como acessar diretamente os atributos
 * Pois são caracteristicas fieis a classe e por isso são privadas.
 * get: Para uma classe obter o valor do atributo
 * set: Para uma classe setar o valor do atributo
 *
 * Aqui usamos um padrão de nomeclatura internacional em Inglês
 *
 */
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
