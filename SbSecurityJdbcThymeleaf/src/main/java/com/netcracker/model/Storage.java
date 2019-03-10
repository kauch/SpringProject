package com.netcracker.model;


import javax.persistence.*;

@Entity
@Table(schema = "delivery_schema", name = "storage")
public class Storage {

    @Id
    @GeneratedValue
    @Column(name = "storage_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "storage_name")
    private String storageName;

    @Column(name = "capicity")
    private int slots;

    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "town")
    private String town;

    @Column(name = "adress")
    private Adress adress;          // check this

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

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }



}
