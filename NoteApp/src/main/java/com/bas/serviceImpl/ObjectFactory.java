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
        return new CollectionController();
    }

    public static INote createNote(String title, String body) {
        return new Note(title, body);
    }

    public static MainFormController createMainFormController( CollectionController cC, Stage stage) {
        return new MainFormController(cC,stage);
    }

    public  static AddFormController createAddFormController(CollectionController controller, Stage stage,MainFormController mfc) {
        return new AddFormController(controller,stage,mfc);
    }

    public static EditFormController CreateEditFormController(CollectionController controller, Stage stage,MainFormController mfc) {
        return new EditFormController(controller,stage,mfc);
    }
}
