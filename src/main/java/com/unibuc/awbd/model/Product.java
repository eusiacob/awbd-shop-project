package com.unibuc.awbd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Can not be empty!")
    @Size(min = 3, max = 100, message = "The name must be between 3 and 100 characters!")
    private String name;

    @NotNull(message = "Can not be empty!")
    @Size(min = 10, max = 500, message = "The description must be between 10 and 500 characters!")
    private String description;

    @NotNull(message = "Can not be empty!")
    @DecimalMin(value = "0.01", message = "The price must be greater than 0!")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "product_distributor",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "distributor_id")
    )
    @NotNull
    private List<Distributor> distributors = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Distributor> getDistributors() {
        return distributors;
    }

    public void setDistributors(List<Distributor> distributors) {
        this.distributors = distributors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
