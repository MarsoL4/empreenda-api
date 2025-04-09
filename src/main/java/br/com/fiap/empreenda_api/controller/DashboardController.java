package br.com.fiap.empreenda_api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.empreenda_api.repository.ProductRepository;
import br.com.fiap.empreenda_api.repository.SaleRepository;
import io.swagger.v3.oas.annotations.Operation;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    //Logger para registrar as operações do controller
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SaleRepository saleRepository;

    @GetMapping
    @Operation(
        summary = "Retorna as informações necessárias para o dashboard",
        description = "Retorna as informações do dashboard a partir dos dados de produtos e vendas cadastrados" 
    )
    @CacheEvict(value = {"products", "sales"}, allEntries = true)
    public Map<String, Object> resumo() {
        
        log.info("Efetuando Operações de Cálculo do Dashboard!");

        Map<String, Object> data = new HashMap<>();

        var produtos = productRepository.findAll();
        var vendas = saleRepository.findAll();

        int estoqueAtual = produtos.stream().mapToInt(p -> p.getQuantidade()).sum();
        int produtosBaixoEstoque = (int) produtos.stream().filter(p -> p.getQuantidade() < 300).count();
        int totalProdutos = produtos.size();

        int vendasHoje = vendas.size();
        double faturamento = vendas.stream().mapToDouble(v -> v.getValorTotal()).sum();
        int pagos = (int) vendas.stream().filter(v -> v.getStatus().equalsIgnoreCase("Confirmado")).count();
        int pendentes = (int) vendas.stream().filter(v -> v.getStatus().equalsIgnoreCase("Pendente")).count();

        data.put("vendasHoje", vendasHoje);
        data.put("estoqueAtual", estoqueAtual);
        data.put("pagos", pagos);
        data.put("pendentes", pendentes);
        data.put("faturamentoAtual", "R$ " + String.format("%.2f", faturamento));
        data.put("faturamentoMes", "R$ " + String.format("%.2f", faturamento));
        data.put("gastosMensais", "R$ 12.300,00"); // fixo
        data.put("proximoPagamento", "30/03/2025"); // fixo
        data.put("produtosBaixoEstoque", produtosBaixoEstoque);
        data.put("totalProdutos", totalProdutos);

        return data;
    }
}