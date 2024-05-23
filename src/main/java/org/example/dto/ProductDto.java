package org.example.dto;

import lombok.*;
import org.example.entity.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDto implements DTO{
    private Long id;
    private String name;
    private String brand;
    private Long price;
    private String size;
    private int quantity;
    private String photo;
    private String category;
    private String discountCreatedAt;
    private String discountFinishedAt;
    private int discount;
}