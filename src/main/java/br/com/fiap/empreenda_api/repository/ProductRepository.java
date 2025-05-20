package br.com.fiap.empreenda_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.empreenda_api.model.Product;
import br.com.fiap.empreenda_api.model.User;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUser(User user);
}