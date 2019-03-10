package com.netcracker.model;

import javax.persistence.*;

@Entity
@Table(schema = "delivery_schema", name = "adresses")
public class Adress {

    @Id
    @GeneratedValue
    @Column(name = "adress_id",updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "country",length = 40, nullable = false)
    private String country;

    @Column(name = "state", length = 40, nullable = false)
    private String state;

    @Column(name = "town", length = 40, nullable = false)
    private String town;

    @Column(name = "street", length = 40, nullable = false)
    private String street;

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
}
