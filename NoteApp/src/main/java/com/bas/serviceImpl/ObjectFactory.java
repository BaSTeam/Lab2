package com.bas.serviceImpl;

import com.bas.model.INote;
import com.bas.model.Note;
import com.bas.service.ICollectionController;
import com.bas.service.ISerializer;
import com.bas.view.AddFormController;
import com.bas.view.EditFormController;
import com.bas.view.MainFormController;
import javafx.stage.Stage;

 public class ObjectFactory {
    public static ISerializer createSerializer() {
        return new Serializer();
    }

    public  static ICollectionController createCollectionController(ISerializer serializer) {
        return new CollectionController(serializer);
    }

    public static INote createNote(String title, String body) {
        return new Note(title, body);
    }
}
