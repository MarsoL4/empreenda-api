package br.com.fiap.empreenda_api.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.empreenda_api.model.Product;
import br.com.fiap.empreenda_api.model.Sale;
import br.com.fiap.empreenda_api.repository.ProductRepository;
import br.com.fiap.empreenda_api.repository.SaleRepository;
import jakarta.annotation.PostConstruct;

@Component
public class DataBaseSeeder {
    
    @Autowired 
    private ProductRepository productRepository;

    @Autowired
    private SaleRepository saleRepository;

    @PostConstruct // Será executado após a injeção de dependências e inicialização do projeto
    public void init() {
        productRepository.saveAll(List.of(
            Product.builder()
                .nome("Água Mineral 500ml")
                .descricao("Garrafa de água mineral sem gás")
                .quantidade(1000)
                .preco(2.50)
                .build(),

            Product.builder()
                .nome("Gelo Tradicional 5kg")
                .descricao("Saco de gelo tradicional")
                .quantidade(800)
                .preco(6.00)
                .build(),

            Product.builder()
                .nome("Gelo de Coco 1kg")
                .descricao("Cubos de gelo sabor coco")
                .quantidade(400)
                .preco(8.90)
                .build()
        ));

        saleRepository.saveAll(List.of(
            Sale.builder()
                .cliente("João Silva")
                .quantidade(10)
                .valorTotal(89.00)
                .formaPagamento("Pix")
                .status("Confirmado")
                .build(),

            Sale.builder()
                .cliente("Maria Oliveira")
                .quantidade(5)
                .valorTotal(30.00)
                .formaPagamento("Cartão")
                .status("Pendente")
                .build(),

            Sale.builder()
                .cliente("Carlos Souza")
                .quantidade(2)
                .valorTotal(12.00)
                .formaPagamento("Dinheiro")
                .status("Confirmado")
                .build()
        ));
    }
}