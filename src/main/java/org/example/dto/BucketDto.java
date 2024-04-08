package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Product;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BucketDto {
    private Long id;
    private Long user_id;
    private Product product;
    private int quantity;

}