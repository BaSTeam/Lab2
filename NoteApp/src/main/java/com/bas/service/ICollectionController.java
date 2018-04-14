package com.bas.service;

import com.bas.model.INote;
import com.bas.model.Note;
import javafx.collections.ObservableList;

import java.util.List;

public interface ICollectionController {
    boolean add(INote note);
    boolean delete(INote note);
    boolean replace(INote oldNote, Note newNote);
   List<INote> getListOfObjects();
   ISerializer getSerializer();
   void setListOfObjects(List<INote> notes);
}
