package com.netcracker.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "delivery_schema", name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 5462266573137446411L;

	@Id
	@GeneratedValue
	@Column(name = "product_id", nullable = false, updatable = false, unique = true)
	private Long productId;

	@Column(name = "product_name", length = 36)
	private String productName;

	@Column(name = "producer", length = 36)
	private String producer;

	@Column(name = "unit", length = 36)
	private String unit;

	@ManyToMany(targetEntity = Order.class, mappedBy = "products")
	private Set<Order> orders = new HashSet<>();

	public Product() {
		super();
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

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

}
