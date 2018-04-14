package com.bas.serviceImpl;

import com.bas.model.INote;
import com.bas.service.ISerializer;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Serializer implements ISerializer {
    private static final String FILE_PATH = "";

    @Override
    public List<INote> get() {
        File file = new File(FILE_PATH);
        try (ObjectInputStream out = new ObjectInputStream(new FileInputStream(file + ".dat"))) {
            return (LinkedList<INote>) out.readObject();

        } catch (Exception e) {
            return new LinkedList<>();
        }

    }

    @Override
    public Boolean save(List<INote> notesList) {
        File file = new File(FILE_PATH);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file + ".dat"))) {
            out.writeObject(notesList);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
