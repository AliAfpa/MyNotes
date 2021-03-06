package com.example.afpa1442.mynotes.classes;

/**
 * Created by afpa1442 on 20/10/16.
 */

public class Project {

    private int id;
    private String name;

    public Project() {}

    public Project(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
