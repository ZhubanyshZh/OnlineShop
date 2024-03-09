package org.example.dto;

public class BucketDto {
    private Long id;

    private Long user_id;

    public BucketDto(Long id, Long userId) {
        this.id = id;
        user_id = userId;
    }

    public BucketDto() {
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}