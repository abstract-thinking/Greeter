package org.example;

import com.github.javafaker.Faker;
import org.example.persistence.VisitorsFileHandler;
import org.example.domain.Guest;
import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VisitorsFileHandlerTest {

    private static final Guest GUEST = new Guest(new Faker().name().firstName());

    @Order(1)
    @Test
    public void shouldSave() {
        Set<Guest> guests = new HashSet<>();
        guests.add(GUEST);

        assertDoesNotThrow(() -> VisitorsFileHandler.save(guests));
    }

    @Order(2)
    @Test
    public void shouldLoad() {
        Set<Guest> guests = VisitorsFileHandler.load();

        assertThat(guests).containsExactly(GUEST);
    }
}