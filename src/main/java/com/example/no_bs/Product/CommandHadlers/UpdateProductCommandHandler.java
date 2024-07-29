package com.example.no_bs.Product.CommandHadlers;

import com.example.no_bs.Command;
import com.example.no_bs.Exceptions.ProductNotFoundException;
import com.example.no_bs.Product.Model.Product;
import com.example.no_bs.Product.Model.UpdateProductCommand;
import com.example.no_bs.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UpdateProductCommandHandler implements Command<UpdateProductCommand, ResponseEntity> {
    @Autowired private ProductRepository productRepository;
    @Override
    public ResponseEntity execute(UpdateProductCommand command) {
        Optional<Product> optionalProduct = productRepository.findById(command.getId());
        if (optionalProduct.isEmpty()){
            throw new ProductNotFoundException();
        }

        Product product = command.getProduct();
        product.setId(command.getId());
        productRepository.save(product);
        return ResponseEntity.ok().build();
    }
}
