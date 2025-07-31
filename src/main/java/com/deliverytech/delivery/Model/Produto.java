package com.deliverytech.delivery.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Anotação que informa ao JPA que esta classe é uma entidade
public class Produto {

    @Id // Define que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define que o ID será gerado automaticamente pelo banco
    private Long id;
    private String nome;
    private double preco;

    // Getters e Setters
    // (Você pode gerar automaticamente na sua IDE: Alt+Insert -> Getter and Setter)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}    
