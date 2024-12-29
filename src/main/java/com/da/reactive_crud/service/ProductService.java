package com.da.reactive_crud.service;

import com.da.reactive_crud.dto.ProductDTO;
import com.da.reactive_crud.entity.Product;
import com.da.reactive_crud.repo.ProductRepository;
import com.da.reactive_crud.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Flux<ProductDTO> getProducts()
    {
        return productRepository.findAll().map(AppUtils::entityToDTO);

    }

    public Mono<ProductDTO> getProduct(String id)
    {
        return productRepository.findAllById(id).map(AppUtils::entityToDTO);
// error in repository extention....
    }


    public Flux<ProductDTO> getProductInRange(double min, double max)
    {
        return productRepository.findByPriceBetween(Range.closed(min,max));
    }


    public Mono<ProductDTO> saveProduct(Mono<ProductDTO> productDTOMono)
    {
       return productDTOMono.map(AppUtils::DTOtToEntity)
                .flatMap(productRepository::insert)
                .map(AppUtils::entityToDTO);

    }

    public Mono<ProductDTO> updateProduct(Mono<ProductDTO> productDTOMono, String id)
    {
       return productRepository.findById(id)
                .flatMap(p-> productDTOMono.map(AppUtils::DTOtToEntity)
                .doOnNext(e-> e.setId(id)))
                        .flatMap(productRepository::save)
                        .map(AppUtils::entityToDTO);
    }


    public Mono<Void> deleteProduct(String id)
    {
         return productRepository.deleteById(id);
    }

}
