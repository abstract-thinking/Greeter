package org.example;

import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VisitorSaverTest {

    @Order(1)
    @Test
    public void shouldSave() {
        Set<Visitor> visitors = new HashSet<>();
        visitors.add(new Visitor("Roger"));

        assertDoesNotThrow(() -> VisitorSaver.save(visitors));
    }

    @Order(2)
    @Test
    public void shouldLoad() {
        Set<Visitor> visitors = VisitorSaver.load();

        assertThat(visitors).containsExactly(new Visitor("Roger"));
    }
}