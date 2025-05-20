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
    private String nome;

    private String descricao;

    @PositiveOrZero
    @NotNull(message = "A quantidade do produto não pode ser nula")
    private int quantidade;

    @Positive
    @NotNull(message = "O preço do produto não pode ser nulo")
    private double preco;
}