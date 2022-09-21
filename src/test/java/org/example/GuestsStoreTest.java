package org.example;

import com.github.javafaker.Faker;
import org.example.persistence.GuestStore;
import org.example.domain.Guest;
import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GuestsStoreTest {

    private static final Guest GUEST = new Guest(new Faker().name().firstName());

    @Order(1)
    @Test
    public void shouldSaveGuests() {
        Set<Guest> guests = new HashSet<>();
        guests.add(GUEST);

        assertDoesNotThrow(() -> GuestStore.save(guests));
    }

    @Order(2)
    @Test
    public void shouldLoadGuests() {
        Set<Guest> guests = GuestStore.load();

        assertThat(guests).containsExactly(GUEST);
    }
}