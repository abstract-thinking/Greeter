package org.example;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VisitorsFileHandlerTest {

    private static final Visitor VISITOR = new Visitor(new Faker().name().firstName());

    @Order(1)
    @Test
    public void shouldSave() {
        Set<Visitor> visitors = new HashSet<>();
        visitors.add(VISITOR);

        assertDoesNotThrow(() -> VisitorsFileHandler.save(visitors));
    }

    @Order(2)
    @Test
    public void shouldLoad() {
        Set<Visitor> visitors = VisitorsFileHandler.load();

        assertThat(visitors).containsExactly(VISITOR);
    }
}