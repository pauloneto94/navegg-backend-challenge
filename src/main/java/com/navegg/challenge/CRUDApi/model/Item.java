package com.navegg.challenge.CRUDApi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Set<Url> urlList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Set<Category> categoryList;

    private String status;

    public Item(String name, Set<Url> urlList, Set<Category> categoryList, String status) {
        this.name = name;
        this.urlList = urlList;
        this.categoryList = categoryList;
        this.status = status;
    }
}
