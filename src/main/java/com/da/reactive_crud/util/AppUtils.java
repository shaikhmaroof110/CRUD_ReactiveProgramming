package com.da.reactive_crud.util;


import com.da.reactive_crud.dto.ProductDTO;
import com.da.reactive_crud.entity.Product;
import org.springframework.beans.BeanUtils;

public class AppUtils {

    // Utils package we are using for convertion into entity to DTO or wise wersa.
    public static ProductDTO entityToDTO(Product product)
    {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO); // we can use .copyProperties only if both entity and DTO have same fields.
        return productDTO;
    }


    public static ProductDTO DTOtToEntity(ProductDTO productDTO)
    {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product); // we can use .copyProperties only if both entity and DTO have same fields.
        return productDTO;
    }
}
