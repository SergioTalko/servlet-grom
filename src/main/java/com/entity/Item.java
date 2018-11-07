package com.entity;

import java.util.Date;
import java.util.Random;

public class Item {


    private Long id;
    private String name;
    private Date dateCreated;
    private Date lastUpdatedDate;
    private String description;


    public Item(String name, String description) {
        long range = 1234567L;
        Random r = new Random();
        this.id = (long)(r.nextDouble()*range);
        this.name = name;
        this.description = description;
    }

    public Item() {
        long range = 1234567L;
        Random r = new Random();
        this.id = (long)(r.nextDouble()*range);
        this.dateCreated = new Date();
        this.lastUpdatedDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateCreated=" + dateCreated +
                ", lastUpdatedDate=" + lastUpdatedDate +
                ", description='" + description + '\'' +
                '}';
    }
}
