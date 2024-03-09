package org.example.dto;

public class ShoesDto extends ProductDto{
    public ShoesDto(Long id, String brand, Long price, Long amount, String size) {
        super(id, brand, price, amount, size);
        this.cat_id = 2L;
    }

    public ShoesDto() {
    }
}