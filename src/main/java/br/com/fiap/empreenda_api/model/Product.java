package br.com.fiap.empreenda_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do produto não pode ficar em branco")
    @NotNull(message = "O nome do produto não pode ser vazio")
    @Size(min = 3)
    private String nome;

    @Size(min = 5, message = "A descrição do Produto deve ter no mínimo 5 caracteres")
    private String descricao;

    @PositiveOrZero
    @NotNull(message = "A quantidade do produto não pode ser nula")
    private int quantidade;

    @Positive
    @NotNull(message = "O preço do produto não pode ser nulo")
    private double preco;
}