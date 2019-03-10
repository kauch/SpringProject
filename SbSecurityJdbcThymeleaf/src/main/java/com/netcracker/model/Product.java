package com.netcracker.model;

import javax.persistence.*;

@Entity
@Table(schema = "delivery_schema", name = "product")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id", nullable = false, updatable = false)
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "producer")
    private String producer;

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

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    private String unit;
}
