package org.example.control;

import org.example.domain.Visit;
import org.example.persistence.VisitStore;

import static java.util.Objects.requireNonNull;

public class Greeter {

    private final VisitStore visitStore;

    public Greeter(VisitStore visitStore) {
        this.visitStore = requireNonNull(visitStore);
    }

    public String greet(String firstName) {
        final Visit visit = findGuest(firstName);

        visit.newVisit();

        return greet(firstName, visit);
    }

    private Visit findGuest(String firstName) {
        return visitStore.find(firstName).orElseGet(() -> createAndStoreVisit(firstName));
    }

    private Visit createAndStoreVisit(String firstName) {
        final Visit visit = new Visit();
        visitStore.put(firstName, visit);

        return visit;
    }

    private static String greet(String firstName, Visit visit) {
        if (visit.isFirst()) {
            return "Hello, " + firstName + "!";
        } else if (visit.isSecond()) {
            return "Welcome back, " + firstName + "!";
        } else {
            String greeting = "Hello my good friend, " + firstName + "!";
            if (visit.isPlatinum()) {
                greeting += " Congrats! You are now a platinum guest!";
            }

            return greeting;
        }
    }

    public void save() {
        visitStore.save();
    }
}
