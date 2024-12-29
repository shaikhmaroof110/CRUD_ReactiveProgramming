package com.da.reactive_crud.controller;

import com.da.reactive_crud.dto.ProductDTO;
import com.da.reactive_crud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Flux<ProductDTO> getProducts()
    {
        return productService.getProducts();
    }

//    @GetMapping("/id")
//    public Mono<ProductDTO> Product(String id)
//    {
//        return productService.getProduct(id);
//    }

    @GetMapping("/product-range")
    public Flux<ProductDTO> getProductBetweenRange(@RequestParam("min") double min,@RequestParam("max") double max)
    {
        return productService.getProductInRange(max,min);
    }

    @PostMapping
    public Mono<ProductDTO> saveProduct(@RequestBody Mono<ProductDTO> productDTOMono)
    {
        return productService.saveProduct(productDTOMono);
    }

    @PutMapping("/update/{id}")
    public Mono<ProductDTO> updateProduct(@RequestBody Mono<ProductDTO> productDTOMono, @PathVariable String id)
    {
        return productService.updateProduct(productDTOMono,id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteProduct(String id)
    {
        return productService.deleteProduct(id);
    }
}
