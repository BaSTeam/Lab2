package com.bas.serviceImpl;

import com.bas.model.INote;
import com.bas.model.Note;
import com.bas.service.ICollectionController;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.List;

public class CollectionController implements ICollectionController {
    private LinkedList<INote> notes = new LinkedList<>();
    @Override
    public boolean add(INote note) {
        try {
            notes.add(note);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean delete(INote note) {
        try {
            notes.remove(note);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean replace(INote oldNote, Note newNote) {
        notes.remove(oldNote);
        notes.add(newNote);
        return false;
    }

    @Override
    public List<INote> getListOfObjects() {
        return notes;
    }

}
