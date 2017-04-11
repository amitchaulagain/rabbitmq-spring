package com.messaging;

/**
 * Created by amit on 4/5/17.
 */

public class Customer {
    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    int id;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
