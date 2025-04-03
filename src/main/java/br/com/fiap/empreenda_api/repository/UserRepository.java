package br.com.fiap.empreenda_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.empreenda_api.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndSenha(String email, String senha);
}