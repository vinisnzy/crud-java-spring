package com.vinisnzy.CRUD.domain.product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@EqualsAndHashCode(of = "id")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private Boolean active;

    public Product(RequestProductDTO data) {
        this.name = data.name();
        this.price = data.price();
        this.active = true;
    }
}
