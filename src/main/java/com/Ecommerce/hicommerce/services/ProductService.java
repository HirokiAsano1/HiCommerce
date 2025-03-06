package com.Ecommerce.hicommerce.services;

import com.Ecommerce.hicommerce.dto.ProductDTO;
import com.Ecommerce.hicommerce.entities.Product;
import com.Ecommerce.hicommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
       Optional <Product> result =  productRepository.findById(id);
       Product product = result.get();
        return new ProductDTO(product);

    }

    @Transactional(readOnly = true)
    public  Page<ProductDTO> findAll(Pageable pageable){
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(ProductDTO::new);
    }

    @Transactional
    public  ProductDTO insert (ProductDTO productDTO){

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());

        product = productRepository.save(product);
        return new ProductDTO(product);
    }
}
