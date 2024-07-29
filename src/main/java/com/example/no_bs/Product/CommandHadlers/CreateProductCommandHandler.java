package com.example.no_bs.Product.CommandHadlers;

import com.example.no_bs.Command;
import com.example.no_bs.Exceptions.ProductNotValidException;
import com.example.no_bs.Product.Model.Product;
import com.example.no_bs.Product.ProductRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateProductCommandHandler implements Command<Product, ResponseEntity> {
    @Autowired private ProductRepository productRepository;
    @Override
    public ResponseEntity execute(Product product) {

        validateProduct(product);

        productRepository.save(product);
        return ResponseEntity.ok().build();
    }

    private void validateProduct(Product product){
        if (StringUtils.isBlank(product.getName())){
            throw new ProductNotValidException("Product name cannot be empty");
        }
        if (StringUtils.isBlank(product.getDescription())){
            throw new ProductNotValidException("Product description cannot be emoty");
        }
        if (product.getPrice() <= 0.0){
            throw new ProductNotValidException("Product price cannot be negative");
        }
        if (product.getQuantity() <= 0){
            throw new ProductNotValidException("Product quantity cannot be negative");
        }
    }
}
