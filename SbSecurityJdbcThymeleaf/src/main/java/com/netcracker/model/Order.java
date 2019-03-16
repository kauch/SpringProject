package com.netcracker.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.netcracker.enums.OrderStatus;

import lombok.Data;

@Data
@Entity
@Table(schema = "delivery_schema", name = "order")
public class Order implements Serializable {

	private static final long serialVersionUID = -8934549699887562409L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_gen_order")
	@SequenceGenerator(name = "delivery_gen_order", initialValue = 1, allocationSize = 1, schema = "delivery_schema")
	@Column(name = "order_id", updatable = false, nullable = false, unique = true)
	private Long orderId;

	@Column(name = "dest_point", length = 36, nullable = false)
	private String destPoint;

	@Column(name = "weight", updatable = false, nullable = false)
	private int weight;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@ManyToOne(targetEntity = Users.class)
	@JoinColumn(name = "owner_id", referencedColumnName = "user_id")
	private Users user;

	@ManyToMany(targetEntity = Product.class, fetch = FetchType.EAGER)
	@JoinTable(schema = "delivery_schema", name = "orders_products", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id"), inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"))
	private Set<Product> products = new HashSet<>();

	@ManyToOne(targetEntity = Storage.class)
	@JoinColumn(name = "stock", referencedColumnName = "storage_id")
	private Storage storage;

}
