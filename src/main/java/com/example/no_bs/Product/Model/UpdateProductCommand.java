package com.example.no_bs.Product.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductCommand {
    private int id;
    private Product product;

    public UpdateProductCommand(int id, Product product) {
        this.id = id;
        this.product = product;
    }
}
