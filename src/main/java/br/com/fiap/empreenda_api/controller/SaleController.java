package br.com.fiap.empreenda_api.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.empreenda_api.model.Sale;
import br.com.fiap.empreenda_api.repository.SaleRepository;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SaleRepository repository;

    @GetMapping
    public List<Sale> index(@RequestParam Long usuarioId) {
        return repository.findAll().stream()
                .filter(s -> s.getUsuarioId().equals(usuarioId))
                .toList();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Sale create(@RequestBody @Valid Sale sale) {
        log.info("Cadastrando venda para " + sale.getCliente());
        return repository.save(sale);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> get(@PathVariable Long id) {
        log.info("Buscando venda " + id);
        return ResponseEntity.ok(getSale(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Sale> delete(@PathVariable Long id) {
        log.info("Deletando venda " + id);
        repository.delete(getSale(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
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