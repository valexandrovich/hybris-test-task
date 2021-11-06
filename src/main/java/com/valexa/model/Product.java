package com.valexa.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer price;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    @Column(name = "created_at")
    private Date createdAt;
}
