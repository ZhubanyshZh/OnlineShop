package org.example.dto;

public class ProductDto {
    protected Long id;
    protected Long cat_id;
    protected String brand;
    protected Long price;
    protected Long amount;
    protected String size;

    public ProductDto(Long id, String brand, Long price, Long amount, String size) {
        this.id = id;
        this.brand = brand;
        this.price = price;
        this.amount = amount;
        this.size = size;
    }

    public ProductDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCat_id() {
        return cat_id;
    }

    public void setCat_id(Long cat_id) {
        this.cat_id = cat_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}