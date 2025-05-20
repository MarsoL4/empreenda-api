package br.com.fiap.empreenda_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @NotBlank(message = "Cliente não pode estar em branco")
    @NotNull(message = "Não é possível cdastrar uma venda com cliente nulo")
    private String cliente;

    @Positive
    @NotNull(message = "A quantidade da venda não pode ser nulo")
    private int quantidade;

    @Positive
    @NotNull(message = "O valor total da venda não pode ser nulo")
    private double valorTotal;

    @NotNull(message = "A forma de pagamento da venda não pode ser nula")
    @Enumerated(EnumType.STRING)
    private FormaPagamentoType formaPagamento;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusSaleType status;

    @ManyToOne
    @JsonIgnore 
    private User user;
}