package org.example.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private boolean isBought;

    public Product(String name) {
        this.name = name;
    }

    public Product(Long id, String name){
        this.id = id;
        this.name = name;
    }
    public Product(Long id, String name, Boolean isBought){
        this.id = id;
        this.name = name;
        this.isBought = isBought;
    }

    public Product(){ }

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

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean isBought) {
        this.isBought = isBought;
    }
}
