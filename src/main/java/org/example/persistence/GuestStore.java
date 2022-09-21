package org.example.persistence;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.Guest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
public class GuestStore {

    private static final String FILENAME = "visitors.ser";

    private final Set<Guest> guests;

    public GuestStore() {
        this.guests = load();
    }

    private Set<Guest> load() {
        try (FileInputStream fis = new FileInputStream(FILENAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Set<Guest>) ois.readObject();
        } catch (IOException ioe) {
            log.error("Error reading file", ioe);
        } catch (ClassNotFoundException cnfe) {
            log.error("Error loading visitors", cnfe);
        }

        return new HashSet<>();
    }

    public Optional<Guest> find(String firstName) {
        return guests.stream()
                .filter(guest -> guest.getFirstName().equalsIgnoreCase(firstName))
                .findFirst();
    }

    public boolean add(Guest guest) {
        return guests.add(guest);
    }

    public void save() {
        try (FileOutputStream fos = new FileOutputStream(FILENAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(guests);
        } catch (IOException ioe) {
            log.error("Problem saving file", ioe);
        }
    }

    public void clear() {
        try (FileOutputStream fos = new FileOutputStream(FILENAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(new HashSet<>());
        } catch (IOException ioe) {
            log.error("Problem saving file", ioe);
        }
    }
}
