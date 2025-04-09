package br.com.fiap.empreenda_api.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.empreenda_api.model.Product;
import br.com.fiap.empreenda_api.repository.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductRepository repository;

    @GetMapping
    @Operation(
        summary = "Listar Produtos",
        description = "Retorna um array com todos os Produtos"
    )
    @Cacheable("products")
    public List<Product> index() {
        return repository.findAll();
    }

    @PostMapping
    @CacheEvict(value = "products", allEntries = true)
    @Operation(
        summary = "Cadastrar novo Produto",
        responses = @ApiResponse(responseCode = "400", description = "Produto Inválido")
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    public Product create(@RequestBody @Valid Product product) {
        log.info("Cadastrando produto " + product.getNome());
        return repository.save(product);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar Produto pelo ID",
        description = "Retorna os dados de um Produto",
        responses = @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    )
    public ResponseEntity<Product> get(@PathVariable Long id) {
        log.info("Buscando produto " + id);
        return ResponseEntity.ok(getProduct(id));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar Produto pelo ID",
        responses = @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    )
    public ResponseEntity<Product> delete(@PathVariable Long id) {
        log.info("Deletando produto " + id);
        repository.delete(getProduct(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Atualizar Produto Existente pelo ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Produto Inválido"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody @Valid Product product) {
        log.info("Atualizando produto " + id + " com " + product);
        getProduct(id);
        product.setId(id);
        repository.save(product);
        return ResponseEntity.ok(product);
    }

    private Product getProduct(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }
}