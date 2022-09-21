package org.example;

import java.io.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class VisitorSaver {

    private static final String FILENAME = "visitors.ser";

    public static void save(Set<Visitor> visitors) {
        try (FileOutputStream fos = new FileOutputStream(FILENAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(visitors);
        } catch (IOException ioe) {
            System.err.println("Problem saving visitors");
            ioe.printStackTrace();
        }
    }

    public static Set<Visitor> load() {
        try (FileInputStream fis = new FileInputStream(FILENAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Set<Visitor>) ois.readObject();
        } catch (IOException ioe) {
            System.err.println("Error reading file");
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error loading VisitorGreeter");
            cnfe.printStackTrace();
        }

        return new HashSet<>();
    }
}
