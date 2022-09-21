package org.example.control;

import org.example.domain.Guest;
import org.example.domain.Visit;
import org.example.persistence.VisitStore;

import static java.util.Objects.requireNonNull;

public class Greeter {

    private final VisitStore visitStore;

    public Greeter(VisitStore visitStore) {
        this.visitStore = requireNonNull(visitStore);
    }

    public String greet(Guest guest) {
        final Visit visit = find(guest);

        visit.attended();

        return greet(visit, guest);
    }

    private Visit find(Guest guest) {
        return visitStore.find(guest).orElseGet(() -> createAndStoreVisit(guest));
    }

    private Visit createAndStoreVisit(Guest guest) {
        final Visit visit = new Visit();
        visitStore.put(guest, visit);

        return visit;
    }

    private static String greet(Visit visit, Guest guest) {
        if (visit.isFirst()) {
            return "Hello, " + guest.firstName() + "!";
        } else if (visit.isSecond()) {
            return "Welcome back, " + guest.firstName() + "!";
        } else {
            String greeting = "Hello my good friend, " + guest.firstName() + "!";
            if (visit.isPlatinum()) {
                greeting += " Congrats! You are now a platinum guest!";
            }

            return greeting;
        }
    }
}
