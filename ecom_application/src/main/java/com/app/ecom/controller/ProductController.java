package com.app.ecom.controller;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecom.dto.ProductRequestDto;
import com.app.ecom.service.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/product")
public class ProductController
{
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping()
    public ResponseEntity<?> createProduct(@RequestBody ProductRequestDto productRequestDto) {
       
        return ResponseEntity.ok(productService.saveProduct(productRequestDto));
    }
    @PutMapping()
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequestDto productRequestDto, @RequestParam Long id) {
        return ResponseEntity.ok(productService.update(productRequestDto, id));
    }
    @GetMapping("allProducts")
    public ResponseEntity<?> getAllProducts() {
       
        return ResponseEntity.ok(productService.fetchAll());
    }
    @DeleteMapping()
    public ResponseEntity<?> deleteProduct(@RequestParam Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProduct(@RequestParam String name) {
        return ResponseEntity.ok(productService.searchProduct(name));
    }

    
    
}
