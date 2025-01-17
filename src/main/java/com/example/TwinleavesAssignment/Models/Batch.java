package com.example.TwinleavesAssignment.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchId;
    private Integer mrp;
    private Integer sp;
    private Integer availableQuantity;
    private Integer purchasePrice;
    private LocalDate inwardedOn;

    public Batch(){
    }
    public Batch(Long batchId, Integer mrp, Integer sp, Integer purchasePrice, Integer availableQuantity, LocalDate inwardedOn) {
        this.batchId = batchId;
        this.mrp = mrp;
        this.sp = sp;
        this.purchasePrice = purchasePrice;
        this.availableQuantity = availableQuantity;
        this.inwardedOn = inwardedOn;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Integer getMrp() {
        return mrp;
    }

    public void setMrp(Integer mrp) {
        this.mrp = mrp;
    }

    public Integer getSp() {
        return sp;
    }

    public void setSp(Integer sp) {
        this.sp = sp;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public LocalDate getInwardedOn() {
        return inwardedOn;
    }

    public void setInwardedOn(LocalDate inwardedOn) {
        this.inwardedOn = inwardedOn;
    }

    public GtinModel getGtinModel() {
        return gtinModel;
    }

    public void setGtinModel(GtinModel gtinModel) {
        this.gtinModel = gtinModel;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private GtinModel gtinModel;

    @JsonIgnore
    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
    private List<Product> productList = new ArrayList<>();

}
