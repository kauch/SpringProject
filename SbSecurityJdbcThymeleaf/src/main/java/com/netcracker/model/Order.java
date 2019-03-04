package com.netcracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "delivery_schema", name = "order")
public class Order {
	@Id
	@GeneratedValue
	@Column(name = "order_id", updatable = false, nullable = false)
	private Long orderId;

	@Column(name = "dest_point", length = 36, nullable = false)
	private String destPoint;

	@Column(name = "weight", updatable = false, nullable = false)
	private int weight;

	@ManyToOne(targetEntity = Users.class)
	@JoinColumn(name = "owner_id", referencedColumnName = "user_id")
	private Users user;

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

}
