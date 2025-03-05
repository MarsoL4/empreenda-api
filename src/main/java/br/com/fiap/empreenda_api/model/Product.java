package br.com.fiap.empreenda_api.model;

import java.time.LocalDateTime;

public class Product {
    private long id;
    private String name;
    private double costPrice;
    private double salePrice;
    private int quantity;
    private int lastQuantityChange;
    private LocalDateTime lastUpdated;

    public Product(long id, String name, double costPrice, double salePrice, int initialQuantity) {
        this.id = id;
        this.name = name;
        this.costPrice = costPrice;
        this.salePrice = salePrice;
        this.quantity = initialQuantity;
        this.lastUpdated = LocalDateTime.now(); // Inicializa a última atualização ao criar o produto
    }

    // Getter para margem de lucro calculada
    public double getProfitMargin() {
        return ((salePrice - costPrice) / costPrice) * 100;
    }

    // Método para atualizar a quantidade, a última mudança e a data da última atualização
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
        return salePrice; // Getter
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

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
}