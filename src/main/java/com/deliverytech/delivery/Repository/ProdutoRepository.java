package com.deliverytech.delivery.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverytech.delivery.Model.Produto;

@Repository // Anotação que define a classe como um componente de repositório do Spring
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // A mágica acontece aqui!
    // JpaRepository já nos fornece métodos como:
    // save(), findById(), findAll(), deleteById(), etc.
    // Não precisamos escrever nenhuma implementação.
}