package br.com.fiap.empreenda_api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.empreenda_api.model.Product;

@RestController
public class ProductController {

    private List<Product> repository = new ArrayList<>();

    // Retorna todos os produtos
    @GetMapping(path = "/products")
    public List<Product> index() {
        return repository;
    }

    // Insere um novo produto
    @PostMapping("/products")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        System.out.println("Cadastrando produto " + product.getName());
        repository.add(product);
        
        return product;
    }

    // Busca um produto pelo id
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> get(@PathVariable Long id) {
        System.out.println("Buscando produto " + id);
        Optional<Product> product = repository.stream()
                .filter(p -> p.getId() == id)
                .findFirst();

        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product.get());
    }

}