package com.netcracker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(schema = "delivery_schema", name = "order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_gen")
	@SequenceGenerator(name = "delivery_gen", initialValue = 1, allocationSize = 1, schema = "delivery_schema")
	@Column(name = "order_id", updatable = false, nullable = false)
	private Long orderId;

	@Column(name = "dest_point", length = 36, nullable = false)
	private String destPoint;

	@Column(name = "weight", updatable = false, nullable = false)
	private int weight;

	@ManyToOne(targetEntity = Users.class)
	@JoinColumn(name = "owner_id", referencedColumnName = "user_id")
	private Users user;
	
	@ManyToMany(targetEntity = Product.class, fetch = FetchType.EAGER)
	@JoinTable(schema = "delivery_schema", name = "orders_products", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id"), inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"))
	private Set<Product> products = new HashSet<>();

	@ManyToOne(targetEntity = Storage.class)
	@JoinColumn(name = "orders_id", referencedColumnName = "storage_id")
	private Storage storage;
	
	public Order() {
		//
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getDestPoint() {
		return destPoint;
	}

	public void setDestPoint(String destPoint) {
		this.destPoint = destPoint;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}
}
