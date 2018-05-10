package com.bas.view;

import com.bas.model.INote;
import com.bas.service.ICollectionController;
import com.bas.serviceImpl.ObjectFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Engine extends Application {
    private ICollectionController collectionController;
    private Stage mainFormStage = new Stage();
    private Stage addFormStage = new Stage();
    private Stage editFormStage = new Stage();
    private MainFormController mainFormController;
    private EditFormController editFormController;

    public static Engine getEngine() {
        return engine;
    }

    private static Engine engine;

    public static void main(String[] args) {
        Application.launch(Engine.class);
    }
    public void saveChanges ()
    {
        collectionController.getSerializer().save(collectionController.getListOfObjects());
    }
    @Override
    public void start(Stage primaryStage) {
        this.mainFormStage = primaryStage;
        engine = this;
        initComponents();
        initPanes();
        initEventHandlers();
        mainFormStage.show();
    }

    private void initComponents() {
        collectionController = ObjectFactory.createCollectionController(ObjectFactory.createSerializer());
    }

    private void initPanes() {
        try {

            mainFormStage.setTitle("NotePad");
            FXMLLoader loader1 = new FXMLLoader(Engine.class.getResource("MainForm.fxml"));
            loader1.setControllerFactory(c-> new MainFormController(collectionController,mainFormStage));
            Parent mainPane = loader1.load();
            Scene scene = new Scene(mainPane);
            mainFormStage.setScene(scene);
            mainFormController = loader1.getController();
            loadNotesFromFile();
            FXMLLoader loader2 = new FXMLLoader(Engine.class.getResource("AddForm.fxml"));
            loader2.setControllerFactory(c-> new AddFormController(collectionController,loader1.getController()));
            Parent addPane = loader2.load();
            scene = new Scene(addPane);
            addFormStage = new Stage();
            addFormStage.setScene(scene);
            addFormStage.hide();
            addFormStage.setTitle("NotePad add");
            FXMLLoader loader3 = new FXMLLoader(Engine.class.getResource("EditForm.fxml"));
            loader3.setControllerFactory(c-> new EditFormController (collectionController,editFormStage,loader1.getController()));
            Parent editPane = loader3.load();
            editFormController = loader3.getController();
            scene = new Scene(editPane);
            editFormStage = new Stage();
            editFormStage.setScene(scene);
            editFormStage.setTitle("NotePad Edit");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadNotesFromFile() {
        collectionController.setListOfObjects(collectionController.getSerializer().get());
        mainFormController.setNotes(collectionController.getSerializer().get());
    }

    private void initEventHandlers() {
        addFormStage.setOnCloseRequest(we -> mainFormStage.show());
        editFormStage.setOnCloseRequest(we -> mainFormStage.show());
        editFormStage.setOnCloseRequest(we -> mainFormStage.show());
    }
    public void addButtonClicked()
    {
        mainFormStage.hide();
        addFormStage.show();
    }
    public void addFinished()
    {
        mainFormStage.show();
        addFormStage.close();
    }
    public void editButtonClicked(INote note)
    {
        mainFormStage.hide();
        editFormStage.show();
        editFormController.setOldNote(ObjectFactory.createNote(note.getTitle(),note.getContent()));
        editFormController.contentArea.setText(note.getContent());
        editFormController.titleField.setText(note.getTitle());

    }
    public void editFinished()
    {
        mainFormStage.show();
        editFormStage.close();
    }

}
