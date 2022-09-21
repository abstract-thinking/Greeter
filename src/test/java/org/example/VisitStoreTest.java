package org.example;

import com.github.javafaker.Faker;
import org.example.domain.Visit;
import org.example.persistence.VisitStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class VisitStoreTest {

    private static final String FIRST_NAME = new Faker().name().firstName();

    private static final Visit VISIT = new Visit();

    private final VisitStore visitStore = new VisitStore();

    @BeforeEach
    public void clear() {
        visitStore.clear();
    }


    @Test
    void shouldAddVisit() {
        boolean wasDifferent = visitStore.put(FIRST_NAME, VISIT);

        assertThat(wasDifferent).isTrue();
    }

    @Test
    void shouldNotAddVisit() {
        givenVisit();

        boolean wasDifferent = visitStore.put(FIRST_NAME, VISIT);

        assertThat(wasDifferent).isFalse();
    }

    private void givenVisit() {
        visitStore.put(FIRST_NAME, VISIT);
    }

    @Test
    void shouldFindVisit() {
        givenVisit();

        Optional<Visit> visit = visitStore.find(FIRST_NAME);

        assertThat(visit).isPresent().get().isEqualTo(VISIT);
    }

    @Test
    void shouldNotFindVisit() {
        Optional<Visit> visit = visitStore.find(FIRST_NAME.concat("BLAH"));

        assertThat(visit).isNotPresent();
    }

    @Test
    void shouldSaveVisits() {
        assertDoesNotThrow(visitStore::save);
    }

    @Test
    void shouldLoadVisits() {
        givenVisit();
        this.visitStore.save();

        VisitStore visitStore = new VisitStore();
        Optional<Visit> visit = visitStore.find(FIRST_NAME);

        assertThat(visit).isPresent().get().isEqualTo(VISIT);
    }
}