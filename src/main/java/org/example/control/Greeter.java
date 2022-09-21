package org.example.control;

import org.example.domain.Guest;
import org.example.persistence.GuestStore;

import static java.util.Objects.requireNonNull;

public class Greeter {

    private final GuestStore guestStore;

    public Greeter(GuestStore guestStore) {
        this.guestStore = requireNonNull(guestStore);
    }

    public String createGreeting(String firstName) {
        final Guest guest = findGuest(firstName);

        guest.newVisit();

        return createGreeting(guest);
    }

    private Guest findGuest(String firstName) {
        return guestStore.find(firstName).orElseGet(() -> createAndStoreNewGuest(firstName));
    }

    private Guest createAndStoreNewGuest(String firstName) {
        final Guest newGuest = new Guest(firstName);
        guestStore.add(newGuest);

        return newGuest;
    }

    private static String createGreeting(Guest guest) {
        if (guest.isFirstVisit()) {
            return "Hello, " + guest.getFirstName() + "!";
        } else if (guest.isSecondVisit()) {
            return "Welcome back, " + guest.getFirstName() + "!";
        } else {
            String greeting = "Hello my good friend, " + guest.getFirstName() + "!";
            if (guest.isPlatinumVisit()) {
                greeting += " Congrats! You are now a platinum guest!";
            }

            return greeting;
        }
    }

    public void save() {
        guestStore.save();
    }
}
