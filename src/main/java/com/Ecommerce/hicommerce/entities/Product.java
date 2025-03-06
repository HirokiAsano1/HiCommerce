package com.Ecommerce.hicommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private double price;

    private String imgUrl;

    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<OrderItem>();

    @ManyToMany
    @JoinTable(name = "tb_product_category",
                joinColumns = @JoinColumn(name = "product_id"),
                inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public List<Order> getOrders(){
        return items.stream().map(OrderItem::getOrder).toList();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }



}
