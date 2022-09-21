package org.example.persistence;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.Guest;
import org.example.domain.Visit;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class VisitStore {

    private static final String FILENAME = "visitors.ser";

    private final Map<Guest, Visit> guestVisitMap;

    public VisitStore() {
        this.guestVisitMap = load();
    }

    private Map<Guest, Visit> load() {
        try (FileInputStream fis = new FileInputStream(FILENAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Map<Guest, Visit>) ois.readObject();
        } catch (IOException ioe) {
            log.error("Error reading file", ioe);
        } catch (ClassNotFoundException cnfe) {
            log.error("Error loading visitors", cnfe);
        }

        return new HashMap<>();
    }

    public Optional<Visit> find(Guest guest) {
        return Optional.ofNullable(guestVisitMap.get(guest));
    }

    public boolean put(Guest guest, Visit visit) {
        Visit previousVisit = guestVisitMap.put(guest, visit);

        return !visit.equals(previousVisit);
    }

    public void save() {
        save(guestVisitMap);
    }

    private static void save(Map<Guest, Visit> guestVisitMap) {
        try (FileOutputStream fos = new FileOutputStream(FILENAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(guestVisitMap);
        } catch (IOException ioe) {
            log.error("Problem saving file", ioe);
        }
    }

    // Open for testing
    public void clear() {
        save(new HashMap<>());
    }
}
