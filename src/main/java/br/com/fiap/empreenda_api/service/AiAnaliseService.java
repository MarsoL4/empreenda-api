package br.com.fiap.empreenda_api.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.empreenda_api.repository.ProductRepository;
import br.com.fiap.empreenda_api.repository.SaleRepository;

@Service
public class AiAnaliseService {

    
    private ChatClient chatClient;

    private SaleRepository saleRepository;
    private ProductRepository productRepository;

    public AiAnaliseService(ChatClient.Builder chatClientBuilder, SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
        this.chatClient = chatClientBuilder
                                    .defaultSystem("Responda sempre em {lang}. Quando fizer uma análise, seja objetivo e responda em no máximo 80 caracteres.")
                                    .defaultOptions(ChatOptions.builder().temperature(0.5).topP(1.0).build())
                                    .build();
    }

    public String getSalesAnalise(String lang){
        var sales = saleRepository.findByUser(null);

        var objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(sales);

            return chatClient
                    .prompt()
                    .user("faça uma análise das minhas vendas: " + json)
                    .system(sp -> sp.param("lang", lang))
                    .call()
                    .content();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "erro ao converter para json";
    }
    
    public String getProductsAnalise(String lang){
        var products = productRepository.findByUser(null);

        var objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(products);

            return chatClient
                    .prompt()
                    .user("faça uma análise do meu estoque de produtos: " + json)
                    .system(sp -> sp.param("lang", lang))
                    .call()
                    .content();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "erro ao converter para json";
    }
}