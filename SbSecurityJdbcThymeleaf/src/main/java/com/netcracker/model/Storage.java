package com.netcracker.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "delivery_schema", name = "storage")
public class Storage implements Serializable {

	private static final long serialVersionUID = -4004529835314522513L;

	@Id
	@GeneratedValue
	@Column(name = "storage_id", nullable = false, updatable = false, unique = true)
	private Long id;

	@Column(name = "storage_name", length = 36)
	private String storageName;

	@Column(name = "capicity")
	private int slots;

	@Column(name = "country", length = 36)
	private String country;

	@Column(name = "state", length = 36)
	private String state;

	@Column(name = "town", length = 36)
	private String town;

	@OneToOne(targetEntity = Address.class)
	@JoinColumn(name = "address", referencedColumnName = "address_id")
	private Address address;

	@OneToMany(targetEntity = Order.class)
	@JoinColumn(name = "stock", referencedColumnName = "storage_id")
	private List<Order> orders;

	public Storage() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	public int getSlots() {
		return slots;
	}

	public void setSlots(int slots) {
		this.slots = slots;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
