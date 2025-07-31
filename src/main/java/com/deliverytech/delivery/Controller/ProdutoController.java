package com.deliverytech.delivery.Controller;

import com.deliverytech.delivery.Model.Produto;
import com.deliverytech.delivery.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Define a classe como um controller REST
@RequestMapping("/api/produtos") // Define a URL base para todos os endpoints neste controller
public class ProdutoController {

    @Autowired // Injeção de dependência: o Spring vai nos fornecer uma instância do ProdutoRepository
    private ProdutoRepository produtoRepository;

    // CREATE -> Criar um novo produto
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoRepository.save(produto);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    // READ -> Listar todos os produtos
    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    // READ -> Buscar produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        // Se o produto for encontrado, retorna 200 OK com o produto.
        // Se não, retorna 404 Not Found.
    }

    // UPDATE -> Atualizar um produto por ID
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoDetalhes) {
        return produtoRepository.findById(id)
                .map(produtoExistente -> {
                    produtoExistente.setNome(produtoDetalhes.getNome());
                    produtoExistente.setPreco(produtoDetalhes.getPreco());
                    Produto produtoAtualizado = produtoRepository.save(produtoExistente);
                    return ResponseEntity.ok(produtoAtualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE -> Deletar um produto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarProduto(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(Produto -> {
                    produtoRepository.delete(Produto);
                    return ResponseEntity.noContent().build(); // Retorna 204 No Content
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }}