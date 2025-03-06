package com.Ecommerce.hicommerce.dto;

import com.Ecommerce.hicommerce.entities.Product;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private long id;

    private String name;

    private String description;

    private double price;

    private String imgUrl;

    public ProductDTO(Product entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imgUrl = entity.getImgUrl();
    }

}
