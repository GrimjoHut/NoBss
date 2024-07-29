package com.example.no_bs.Product.Model;

import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private String description;
    private Double price;

    public ProductDTO(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }
}
