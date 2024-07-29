package com.example.no_bs.Product.CommandHadlers;

import com.example.no_bs.Command;
import com.example.no_bs.Exceptions.ProductNotFoundException;
import com.example.no_bs.Product.Model.Product;
import com.example.no_bs.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteProductCommandHandler implements Command<Integer, ResponseEntity> {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public ResponseEntity execute(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()){
            throw new ProductNotFoundException();
        }
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
        return ResponseEntity.ok().build();
    }
}
