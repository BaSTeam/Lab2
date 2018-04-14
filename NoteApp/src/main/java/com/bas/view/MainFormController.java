package com.bas.view;

import com.bas.model.INote;
import com.bas.service.ICollectionController;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainFormController {
    private ICollectionController collectionController;
    private Stage mainFormStage;
    @FXML
    TextField titleField;
    @FXML
    TextArea contentField;
    @FXML
    ListView<INote> noteListView;

    public MainFormController(ICollectionController controller, Stage stage) {
        collectionController = controller;
        this.mainFormStage = stage;
    }

    @FXML
    void showNoteInfo() {
        INote focusedItem = noteListView.getFocusModel().getFocusedItem();
        if (focusedItem != null) {
            titleField.setText(focusedItem.getTitle());
            contentField.setText(focusedItem.getContent());
        }
    }

    @FXML
    void addButtonClick() {
        Engine.getEngine().addButtonClicked();
    }

    void addNoteToList(INote note) {
        noteListView.getItems().add(note);
    }

    @FXML
    void deleteButtonClick() {
        INote noteToDelete = noteListView.getFocusModel().getFocusedItem();
        if (noteToDelete != null) {
            collectionController.delete(noteToDelete);
            noteListView.getItems().remove(noteToDelete);
            clearNoteInfo();
        }
    }

    @FXML
    void editButtonClick() {
        INote noteToEdit = noteListView.getFocusModel().getFocusedItem();
        if (noteToEdit != null) {
            Engine.getEngine().editButtonClicked(noteToEdit);
        }
    }
    void editNote(INote oldNote, INote newNote)
    {
        noteListView.getItems().remove(oldNote);
        noteListView.getItems().add(newNote);
        clearNoteInfo();
    }

    private void clearNoteInfo() {
        titleField.setText("");
        contentField.setText("");
    }
}
