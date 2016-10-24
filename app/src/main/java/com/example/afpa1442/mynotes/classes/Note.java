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

    public Note(int id, String title, String text, boolean solved, int project_id, int dev_id) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.solved = solved;
        this.project_id = project_id;
        this.dev_id = dev_id;
    }

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
