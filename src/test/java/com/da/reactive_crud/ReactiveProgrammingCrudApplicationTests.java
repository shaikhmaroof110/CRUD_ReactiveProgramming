package com.da.reactive_crud;

import com.da.reactive_crud.controller.ProductController;
import com.da.reactive_crud.dto.ProductDTO;
import com.da.reactive_crud.entity.Product;
import com.da.reactive_crud.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(ProductController.class)
class ReactiveProgrammingCrudApplicationTests {

	// How to test
// we need to add test cases for remaining endpoint...
	@Autowired
	private WebTestClient webTestClient;

	@MockitoBean
	private ProductService productService;

	@Test
	public void addProductTest()
	{
		// we don't want to store the data into DB so we are using the below.
		Mono<ProductDTO> productDTOMono = Mono.just(new ProductDTO("102", "mobile", 1,1000));
		when(productService.saveProduct(productDTOMono)).thenReturn(productDTOMono);
		webTestClient.post().uri("/products")
				.body(Mono.just(productDTOMono), ProductDTO.class )
				.exchange()
				.expectStatus().isOk();
	}

	public void getProducts()
	{
		Flux<ProductDTO>  productDTOFlux = Flux.just(new ProductDTO("102", "mobile", 1,1000),
				new ProductDTO("103", "Airpot", 1,500),
				new ProductDTO("103", "mobile", 5,30000));

		when(productService.getProducts()).thenReturn(productDTOFlux);

		webTestClient.get().uri("/products")
				.exchange()
				.expectStatus().isOk()
				.returnResult(ProductDTO.class)
				.getResponseBody();
	}

}
