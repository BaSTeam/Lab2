package com.bas.serviceImpl;

import com.bas.model.INote;
import com.bas.service.ISerializer;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.LinkedList;
import java.util.List;


public class Serializer implements ISerializer {
    private static final String FILE_PATH = "C:\\Notes\\Storage.dat";

    @Override
    public List<INote> get() {
        try ( InputStream in = new FileInputStream(new File(FILE_PATH))){
            ObjectInputStream out = new ObjectInputStream(in);
            return (LinkedList<INote>) out.readObject();
        }
        catch (ClassNotFoundException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Data file was crashed, data recovery is not possible");
            alert.show();
            return new LinkedList<>();
        }
        catch (FileNotFoundException e)
        {
            return new LinkedList<>();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
            return new LinkedList<>();
        }

    }

    @Override
    public Boolean save(List<INote> notesList) {
        File file = new File(FILE_PATH);
        try {
            file.getParentFile().mkdir();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(notesList);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
