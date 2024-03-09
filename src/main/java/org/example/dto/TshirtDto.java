package org.example.dto;

public class TshirtDto extends ProductDto{
    public TshirtDto(Long id, String brand, Long price, Long amount, String size) {
        super(id, brand, price, amount, size);
        this.cat_id = 3L;
    }

    public TshirtDto() {
    }
}