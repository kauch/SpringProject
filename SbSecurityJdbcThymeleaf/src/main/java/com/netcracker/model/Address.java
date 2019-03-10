package com.netcracker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(schema = "delivery_schema", name = "address")
public class Address {

    @Id
    @GeneratedValue
    @Column(name = "address_id",updatable = false, nullable = false, unique = true)
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
