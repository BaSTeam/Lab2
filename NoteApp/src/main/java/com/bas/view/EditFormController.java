package com.bas.view;

import com.bas.model.INote;
import com.bas.model.Note;
import com.bas.service.ICollectionController;
import com.bas.serviceImpl.ObjectFactory;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditFormController {
    private Stage stage;
    private MainFormController mainFormController;
    private ICollectionController collectionController;
    private Note oldNote;
    @FXML
    TextField titleField;
    @FXML
    TextArea contentArea;


    @FXML
    void editButtonClick() {
        Note newNote = (Note) ObjectFactory.createNote(titleField.getText(),contentArea.getText());
        mainFormController.editNote(oldNote,newNote);
        collectionController.replace(oldNote,newNote);
        Engine.getEngine().editFinished();

    }
    public void setOldNote(INote note)
    {
        oldNote = (Note)note;
    }

    public EditFormController(ICollectionController collectionController, Stage stage, MainFormController mainFormController ) {
        this.stage = stage;
        this.mainFormController = mainFormController;
        this.collectionController = collectionController;
    }
}
