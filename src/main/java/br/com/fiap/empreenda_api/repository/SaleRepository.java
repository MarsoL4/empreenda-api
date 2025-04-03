package br.com.fiap.empreenda_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.empreenda_api.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    
}