package com.da.reactive_crud.repo;

import com.da.reactive_crud.dto.ProductDTO;
import com.da.reactive_crud.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Repository
public interface ProductRepository extends ReactiveCrudRepository <Product, String>{

    Flux<ProductDTO> findByPriceBetween(Range<Double> priceRange);
}
