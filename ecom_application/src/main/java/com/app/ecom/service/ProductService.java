package com.app.ecom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.ecom.dto.ProductRequestDto;
import com.app.ecom.dto.ProductResponseDto;
import com.app.ecom.model.Product;
import com.app.ecom.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public ProductResponseDto saveProduct(ProductRequestDto productRequestDto) 
    {
         Product product = new Product();
        Product dtoProduct=dtoToModel(productRequestDto,product);

        return mapToResponseDto(dtoProduct);
    
    }
    private ProductResponseDto mapToResponseDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setCategory(product.getCategory());
        productResponseDto.setActive(product.getActive());
        productResponseDto.setStockQuantity(product.getStockQuantity());
        productResponseDto.setImageUrl(product.getImageUrl());
        productResponseDto.setCreatedAt(product.getCreatedAt());
        productResponseDto.setUpdatedAt(product.getUpdatedAt());
        
        return productResponseDto;
    }
    private Product dtoToModel(ProductRequestDto productRequestDto,Product product) {
        
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setCategory(productRequestDto.getCategory());
        product.setStockQuantity(productRequestDto.getStockQuantity());
        product.setImageUrl(productRequestDto.getImageUrl());

        return productRepository.save(product);
    }
    public ProductResponseDto update(ProductRequestDto productRequestDto, Long id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return mapToResponseDto(dtoToModel(productRequestDto, existingProduct));
    }
    public List<ProductResponseDto> fetchAll() {
       return productRepository.findAll().stream().filter(product->product.getActive()).map(this::mapToResponseDto).toList();   
    }
    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setActive(false); // Mark as inactive instead of deleting
        productRepository.save(product);
    }
    public List<ProductResponseDto> searchProduct(String name) 
    {
        return productRepository.searchByName(name).stream().map(this::mapToResponseDto).toList();
        
    }

    
}
