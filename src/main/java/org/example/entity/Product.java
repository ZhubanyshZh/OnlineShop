package org.example.entity;

import jakarta.persistence.*;
import jdk.jfr.SettingDefinition;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import reactor.util.annotation.Nullable;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "ms_product")
public class Product implements CustomEntity{

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_brand")
    private String productBrand;

    @Column(name = "price")
    private Long price;

    @Column(name = "size")
    private String size;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column
    private String discountCreatedAt;

    @Column
    private String discountFinishedAt;

    @Column(columnDefinition = "int default 0")
    private int discount = 0;
}
