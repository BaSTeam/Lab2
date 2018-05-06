package com.bas.view;

import com.bas.model.INote;
import com.bas.service.ICollectionController;
import com.bas.serviceImpl.ObjectFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddFormController {
    private ICollectionController collectionController;
    private MainFormController mainFormController;
    @FXML
    TextArea contentArea;
    @FXML
    TextField titleField;

     AddFormController(ICollectionController controller, MainFormController mainFormController) {
        collectionController = controller;
        this.mainFormController = mainFormController;
    }

   private boolean isEmpty(String text)
    {
        if(text.toCharArray().length<1)
            return true;
        for (char character: text.toCharArray()
             ) {
            if (!(character == '\u0020'||character == '\n'))
                return false;
        }
        return true;
    }

    @FXML
    void addButtonClick() {
        if(!isEmpty(titleField.getText())&&!isEmpty(contentArea.getText())) {
            INote note = ObjectFactory.createNote(titleField.getText(), contentArea.getText());
            mainFormController.addNoteToList(note);
            collectionController.add(note);
            Engine.getEngine().addFinished();
            titleField.setText("");
            contentArea.setText("");
            Engine.getEngine().saveChanges();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "You must create title and content");
            alert.show();
        }
    }

}
