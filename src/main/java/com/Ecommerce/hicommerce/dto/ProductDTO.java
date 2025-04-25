package com.Ecommerce.hicommerce.dto;

import com.Ecommerce.hicommerce.entities.Product;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private long id;

    @Size(min = 3, max = 80 , message = "80 CHARACTER LIMIT")
    @NotBlank(message = "Required Field")
    private String name;

    @Size(min = 10,  message = "Min 10 Character")
    @NotBlank(message = "Required Field")
    private String description;

    @Positive(message = "The price needs to be positive")
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
