package com.netcracker.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "delivery_schema", name = "address")
public class Address implements Serializable {

	private static final long serialVersionUID = -7480677280768347426L;

	@Id
	@GeneratedValue
	@Column(name = "address_id", updatable = false, nullable = false, unique = true)
	private Long id;

	@Column(name = "country", length = 36, nullable = false)
	private String country;

	@Column(name = "state", length = 36, nullable = false)
	private String state;

	@Column(name = "town", length = 36, nullable = false)
	private String town;

	@Column(name = "street", length = 36, nullable = false)
	private String street;

	@OneToOne(targetEntity = Storage.class, mappedBy = "address")
	@JoinColumn(name = "address", referencedColumnName = "address_id")
	private Storage storage;

	@ManyToMany(targetEntity = Users.class, mappedBy = "address")
	private Set<Users> users = new HashSet<>();

	public Address() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}

}
