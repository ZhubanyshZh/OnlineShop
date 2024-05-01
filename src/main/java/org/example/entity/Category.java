package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = "ms_category")
@Entity
@Data
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "category_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parentId;

    @OneToMany(mappedBy = "parentId")
    private List<Category> subcategories;

//    @OneToMany(mappedBy = "category")
//    private List<Product> products;
}
