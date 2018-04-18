package com.bas.serviceImpl;

import com.bas.model.INote;
import com.bas.service.ISerializer;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Serializer implements ISerializer {
    private static final String FILE_PATH = "D:\\Storage.dat";

    @Override
    public List<INote> get() {
        try ( InputStream in = new FileInputStream(new File(FILE_PATH))){
            ObjectInputStream out = new ObjectInputStream(in);
            return (LinkedList<INote>) out.readObject();
        } catch (Exception e) {
            return new LinkedList<>();
        }

    }

    @Override
    public Boolean save(List<INote> notesList) {
        File file = new File(FILE_PATH);
        try {
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
