package br.com.fiap.empreenda_api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.empreenda_api.model.User;
import br.com.fiap.empreenda_api.repository.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository repository;

    @PostMapping
    public User register(@RequestBody @Valid User user) {
        return repository.save(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User loginData) {
        Optional<User> user = repository.findByEmailAndSenha(
            loginData.getEmail(),
            loginData.getSenha()
        );

        if (user.isEmpty()) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }

        return user.get();
    }
}