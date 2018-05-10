package com.bas.model;

import java.io.Serializable;

public class Note implements INote ,Serializable {
    private String title;
    private String content;

    public Note(String title, String body) {
        this.title = title;
        this.content = body;
    }

    @Override
    public String getTitle() {
        return title;
    }
    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            Note note = (Note) o;
            return this.title.equals( note.title) && this.content.equals(note.content);
        }
        return false;

    }
}
