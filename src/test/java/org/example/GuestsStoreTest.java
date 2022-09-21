package org.example;

import com.github.javafaker.Faker;
import org.example.domain.Guest;
import org.example.persistence.GuestStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GuestsStoreTest {

    private static final Guest GUEST = new Guest(new Faker().name().firstName());

    private final GuestStore guestStore = new GuestStore();

    @BeforeEach
    public void clear() {
        guestStore.clear();
    }

    @Test
    public void shouldAddGuest() {
        boolean wasAdd = guestStore.add(GUEST);

        assertThat(wasAdd).isTrue();
    }

    @Test
    public void shouldNotAddGuest() {
        givenGuest();

        boolean wasAdd = guestStore.add(GUEST);

        assertThat(wasAdd).isFalse();
    }

    @Test
    public void shouldFindGuest() {
        givenGuest();

        Optional<Guest> guest = guestStore.find(GUEST.getFirstName());

        assertThat(guest).isPresent().get().isEqualTo(GUEST);
    }

    private void givenGuest() {
        guestStore.add(GUEST);
    }

    @Test
    public void shouldNotFindGuest() {
        Optional<Guest> guest = guestStore.find(GUEST.getFirstName().concat("BLAH"));

        assertThat(guest).isNotPresent();
    }

    @Test
    public void shouldSaveGuests() {
        assertDoesNotThrow(guestStore::save);
    }

    @Test
    public void shouldLoadGuests() {
        givenGuest();
        guestStore.save();

        GuestStore guestStore = new GuestStore();
        Optional<Guest> guest = guestStore.find(GUEST.getFirstName());

        assertThat(guest).isPresent().get().isEqualTo(GUEST);
    }
}