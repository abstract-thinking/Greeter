package org.example.control;

import org.example.domain.Guest;
import org.example.persistence.GuestStore;

import java.util.Optional;
import java.util.Set;

public class GreeterCreator {

    private final Set<Guest> guests;

    public GreeterCreator() {
        guests = GuestStore.load();
    }

    public String createGreeting(String name) {
        final Guest guest = findGuest(name);

        guest.newVisit();

        return createGreeterMessage(guest);
    }

    private Guest findGuest(String name) {
        Optional<Guest> knownGuest = guests.stream()
                .filter(g -> g.getName().equalsIgnoreCase(name))
                .findFirst();

        if (knownGuest.isEmpty()) {
            final Guest newGuest = new Guest(name);
            guests.add(newGuest);
            return newGuest;
        } else {
            return knownGuest.get();
        }
    }

    private static String createGreeterMessage(Guest guest) {
        if (guest.isFirstVisit()) {
            return "Hello, " + guest.getName() + "!";
        } else if (guest.isSecondVisit()) {
            return "Welcome back, " + guest.getName() + "!";
        } else {
            String greeting = "Hello my good friend, " + guest.getName() + "!";
            if (guest.isPlatinumVisit()) {
                greeting += " Congrats! You are now a platinum guest!";
            }

            return greeting;
        }
    }

    public void save() {
        GuestStore.save(guests);
    }
}
