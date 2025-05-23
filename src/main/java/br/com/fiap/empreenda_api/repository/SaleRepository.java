package br.com.fiap.empreenda_api.repository;

import br.com.fiap.empreenda_api.model.Sale;
import br.com.fiap.empreenda_api.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SaleRepository extends JpaRepository<Sale, Long>, JpaSpecificationExecutor<Sale> {
    List<Sale> findByUser(User user);
}