package com.bas.view;

import com.bas.model.INote;
import com.bas.model.Note;
import com.bas.service.ICollectionController;
import com.bas.serviceImpl.ObjectFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditFormController {
    private Stage editFormStage;
    private MainFormController mainFormController;
    private ICollectionController collectionController;
    private Note oldNote;
    @FXML
    TextField titleField;
    @FXML
    TextArea contentArea;


    @FXML
    void editButtonClick() {
        if (!isEmpty(titleField.getText()) && !isEmpty(contentArea.getText())) {
            Note newNote = (Note) ObjectFactory.createNote(titleField.getText(), contentArea.getText());
            mainFormController.editNote(oldNote, newNote);
            collectionController.replace(oldNote, newNote);
            Engine.getEngine().editFinished();
            Engine.getEngine().saveChanges();
        }
        else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING,
                        "You must create title and content");
                alert.show();
            }

    }

    private boolean isEmpty(String text) {
        for (char character : text.toCharArray()
                ) {
            if (!(character == '\u0020' || character == '\n'))
                return false;
        }
        return true;
    }

    public void setOldNote(INote note) {
        oldNote = (Note) note;
    }

     EditFormController(ICollectionController collectionController, Stage stage, MainFormController mainFormController) {
        this.editFormStage = stage;
        this.mainFormController = mainFormController;
        this.collectionController = collectionController;
    }
}
