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
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String cliente;

    @Positive
    private int quantidade;

    @Positive
    private double valorTotal;

    @NotBlank
    private String formaPagamento;

    @NotBlank
    private String status;
}