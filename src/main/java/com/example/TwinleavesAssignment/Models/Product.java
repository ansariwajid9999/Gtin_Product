package com.example.TwinleavesAssignment.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private LocalDate createdOn;

    public Product(){
    }

    public Product(Long productId, String productName, LocalDate createdOn) {
        this.productId = productId;
        this.productName = productName;
        this.createdOn = createdOn;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public GtinModel getGtinModel() {
        return gtinModel;
    }

    public void setGtinModel(GtinModel gtinModel) {
        this.gtinModel = gtinModel;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Batch batch;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private GtinModel gtinModel;
}
