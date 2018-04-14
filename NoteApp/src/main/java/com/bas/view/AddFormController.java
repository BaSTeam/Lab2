package com.bas.view;

import com.bas.model.INote;
import com.bas.service.ICollectionController;
import com.bas.serviceImpl.ObjectFactory;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddFormController {
    static final int MINIMAL_TITLE_LENGTH = 3;
    private Stage addFormStage;
    private ICollectionController collectionController;
    private MainFormController mainFormController;
    @FXML
    TextArea contentArea;
    @FXML
    TextField titleField;

    public AddFormController(ICollectionController controller, Stage stage, MainFormController mainFormController) {
        collectionController = controller;
        this.addFormStage = stage;
        this.mainFormController = mainFormController;
    }


    @FXML
    void addButtonClick() {
        INote note = ObjectFactory.createNote(titleField.getText(),contentArea.getText());
        mainFormController.addNoteToList(note);
        collectionController.add(note);
        Engine.getEngine().addFinished();
        titleField.setText("");
        contentArea.setText("");
    }

}
