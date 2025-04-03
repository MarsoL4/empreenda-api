package br.com.fiap.empreenda_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.empreenda_api.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}