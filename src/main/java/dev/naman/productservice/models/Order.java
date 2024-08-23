package dev.naman.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table(name = "orders")
@Getter
@Setter
@Entity
public class Order extends BaseModel {

    @ManyToMany
    @JoinTable(name = "order_products_join_table",
                joinColumns = @JoinColumn(name = "order_id"),
                inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productList;
}
