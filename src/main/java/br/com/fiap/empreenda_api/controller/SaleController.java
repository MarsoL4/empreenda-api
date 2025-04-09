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

import br.com.fiap.empreenda_api.model.Sale;
import br.com.fiap.empreenda_api.repository.SaleRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SaleRepository repository;

    @GetMapping
    @Operation(
        summary = "Listar Vendas",
        description = "Retorna um array com todas as Vendas"
    )
    @Cacheable("sales")
    public List<Sale> index() {
        return repository.findAll();
    }

    @PostMapping
    @CacheEvict(value = "sales", allEntries = true)
    @Operation(
        summary = "Cadastrar uma nova Venda",
        responses = @ApiResponse(responseCode = "400", description = "Venda Inválida")
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    public Sale create(@RequestBody @Valid Sale sale) {
        log.info("Cadastrando venda para " + sale.getCliente());
        return repository.save(sale);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar Venda pelo ID",
        description = "Retorna os dados de uma Venda",
        responses = @ApiResponse(responseCode = "404", description = "Venda não encontrada")
    )
    public ResponseEntity<Sale> get(@PathVariable Long id) {
        log.info("Buscando venda " + id);
        return ResponseEntity.ok(getSale(id));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar Venda pelo ID",
        responses = @ApiResponse(responseCode = "404", description = "Venda não encontrada")
    )
    public ResponseEntity<Sale> delete(@PathVariable Long id) {
        log.info("Deletando venda " + id);
        repository.delete(getSale(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Atualizar Venda Existente pelo ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Venda Inválida"),
        @ApiResponse(responseCode = "404", description = "Venda não encontrada")
    })
    public ResponseEntity<Sale> update(@PathVariable Long id, @RequestBody @Valid Sale sale) {
        log.info("Atualizando venda " + id + " com " + sale);
        getSale(id);
        sale.setId(id);
        repository.save(sale);
        return ResponseEntity.ok(sale);
    }

    private Sale getSale(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venda não encontrada"));
    }
}