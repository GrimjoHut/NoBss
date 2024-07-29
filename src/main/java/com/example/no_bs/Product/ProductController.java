package com.example.no_bs.Product;

import com.example.no_bs.Exceptions.ProductNotFoundException;
import com.example.no_bs.Product.CommandHadlers.CreateProductCommandHandler;
import com.example.no_bs.Product.CommandHadlers.DeleteProductCommandHandler;
import com.example.no_bs.Product.CommandHadlers.UpdateProductCommandHandler;
import com.example.no_bs.Product.Model.Product;
import com.example.no_bs.Product.Model.ProductDTO;
import com.example.no_bs.Product.Model.UpdateProductCommand;
import com.example.no_bs.Product.QueryHadlers.GetAllProductsQueryHandler;
import com.example.no_bs.Product.QueryHadlers.GetProductQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired private ProductRepository productRepository;
    @Autowired private GetAllProductsQueryHandler getAllProductsQueryHandler;
    @Autowired private GetProductQueryHandler getProductQueryHandler;
    @Autowired private CreateProductCommandHandler createProductCommandHandler;
    @Autowired private UpdateProductCommandHandler updateProductCommandHandler;
    @Autowired private DeleteProductCommandHandler deleteProductCommandHandler;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return getAllProductsQueryHandler.execute(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id) {
        return getProductQueryHandler.execute(id);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        return createProductCommandHandler.execute(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        UpdateProductCommand command = new UpdateProductCommand(id, product);
        return updateProductCommandHandler.execute(command);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        return deleteProductCommandHandler.execute(id);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
    }
}
