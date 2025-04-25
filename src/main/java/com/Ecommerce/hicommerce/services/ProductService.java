package com.Ecommerce.hicommerce.services;

import com.Ecommerce.hicommerce.dto.ProductDTO;
import com.Ecommerce.hicommerce.entities.Product;
import com.Ecommerce.hicommerce.repositories.ProductRepository;
import com.Ecommerce.hicommerce.services.exceptions.DatabaseException;
import com.Ecommerce.hicommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
        Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found"));
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
        copyDtoToEntity(productDTO,product);
        product = productRepository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public  ProductDTO update (Long id , ProductDTO productDTO){

        try {
            Product product = productRepository.getReferenceById(id);
            copyDtoToEntity(productDTO,product);
            product = productRepository.save(product);
            return new ProductDTO(product);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Product not found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public  void delete (Long id ){
        if(!productRepository.existsById(id)){
            throw new ResourceNotFoundException("Product not found");
        }
        try {
            productRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }

    }

    private void copyDtoToEntity(ProductDTO productDTO, Product product) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());

    }
}
