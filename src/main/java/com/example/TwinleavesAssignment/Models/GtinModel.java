package com.example.TwinleavesAssignment.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class GtinModel {
    public List<Batch> getBatchList() {
        return batchList;
    }

    public void setBatchList(List<Batch> batchList) {
        this.batchList = batchList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gtin;

    public GtinModel(){
    }
    public GtinModel(Long id, String gtin) {
        this.id = id;
        this.gtin = gtin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGtin() {
        return gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @OneToMany(mappedBy = "gtinModel", cascade = CascadeType.ALL)
    private List<Batch> batchList = new ArrayList<>();

    @JsonIgnore
    @OneToOne
    @JoinColumn
    private Product product;
}
