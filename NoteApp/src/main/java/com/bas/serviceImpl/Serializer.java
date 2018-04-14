package com.bas.serviceImpl;

import com.bas.model.INote;
import com.bas.service.ISerializer;

import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Serializer implements ISerializer {
    private static final String FILE_PATH = "Storage.dat";

    @Override
    public List<INote> get() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(FILE_PATH)).getFile());
        try (ObjectInputStream out = new ObjectInputStream(new FileInputStream(file ))) {
            return (LinkedList<INote>) out.readObject();

        } catch (Exception e) {
            return new LinkedList<>();
        }

    }

    @Override
    public Boolean save(List<INote> notesList) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(FILE_PATH)).getFile());
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(notesList);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
