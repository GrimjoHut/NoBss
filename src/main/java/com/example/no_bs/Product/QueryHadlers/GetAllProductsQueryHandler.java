package com.example.no_bs.Product.QueryHadlers;

import com.example.no_bs.Product.Model.ProductDTO;
import com.example.no_bs.Product.ProductRepository;
import com.example.no_bs.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsQueryHandler implements Query<Void, List<ProductDTO>> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<List<ProductDTO>> execute(Void input) {

        List<ProductDTO> productDTOs = productRepository
                .findAll()
                .stream()
                .map(ProductDTO::new)
                .toList();
        return ResponseEntity.ok(productDTOs);
    }
}
