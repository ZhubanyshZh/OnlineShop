package org.example.dto;

public class JeansDto extends ProductDto{
    public JeansDto(){

    }

    public JeansDto(Long id, String brand, Long price, Long amount, String size) {
        super(id, brand, price, amount, size);
        this.cat_id = 1L;
    }


}