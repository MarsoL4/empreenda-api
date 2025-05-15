package br.com.fiap.empreenda_api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.empreenda_api.model.User;
import br.com.fiap.empreenda_api.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository repository;


    @PostMapping
    @Operation(
        summary = "Cadastrar novo Usuário",
        responses = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Usuário inválido")
        }
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    public User register(@RequestBody @Valid User user) {
        return repository.save(user);
    }

    // @PostMapping("/login")
    // @Operation(
    //     summary = "Autenticar Usuário (Login)",
    //     responses = {
    //         @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
    //         @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    //     }
    // )
    // public User login(@RequestBody User loginData) {
    //     Optional<User> user = repository.findByEmailAndSenha(
    //         loginData.getEmail(),
    //         loginData.getPassword()
    //     );

    //     if (user.isEmpty()) {
    //         throw new RuntimeException("Usuário ou senha inválidos");
    //     }

    //     return user.get();
    // }
}