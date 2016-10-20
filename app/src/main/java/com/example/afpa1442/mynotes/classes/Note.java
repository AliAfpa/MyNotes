package com.example.afpa1442.mynotes.classes;

/**
 * Created by afpa1442 on 20/10/16.
 */

public class Note {

    private int id;
    private String title;
    private String text;
    private boolean solved;
    private int project_id;
    private int dev_id;

    public Note(){}

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public int getProject_id() {
        return project_id;
    }

    public int getDev_id() {
        return dev_id;
    }

}
