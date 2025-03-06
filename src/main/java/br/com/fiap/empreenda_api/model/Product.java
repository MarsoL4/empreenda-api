package br.com.fiap.empreenda_api.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Product {
    private long id;
    private String name;
    private double costPrice;
    private double salePrice;
    private int quantity;
    private int lastQuantityChange;
    private LocalDateTime lastUpdated;

    public Product(long id, String name, double costPrice, double salePrice, int initialQuantity) {
        this.id = Math.abs(new Random().nextLong());
        this.name = name;
        this.costPrice = costPrice;
        this.salePrice = salePrice;
        this.quantity = initialQuantity;
        this.lastUpdated = LocalDateTime.now(); // Inicializa a última atualização ao criar o produto
    }

    public String getProfitMargin() {
        double profitMargin = ((salePrice - costPrice) / costPrice) * 100;
        return String.format("%.1f", profitMargin); // Formata com 1 casa decimal
    }

    // Atualiza a quantidade, a última mudança e a data da última atualização
    public void updateQuantity(int changeInQuantity) {
        this.lastQuantityChange = changeInQuantity;
        this.quantity += changeInQuantity;
        this.lastUpdated = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getLastQuantityChange() {
        return lastQuantityChange;
    }

    public String getLastUpdated() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return lastUpdated.format(formatter); // Formata a data como YYYY-MM-DD
    }
}