package org.example;

import com.github.javafaker.Faker;
import org.example.control.Greeter;
import org.example.domain.Visit;
import org.example.persistence.VisitStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GreeterTest {

    private String firstName;

    @Mock
    private VisitStore visitStore;

    @InjectMocks
    private Greeter greeter;

    @BeforeEach
    public void generateFirstName() {
        firstName = new Faker().name().firstName();
    }

    @Test
    void shouldGreetFirstTime() {
        when(visitStore.find(firstName)).thenReturn(Optional.empty());

        final String greeting = greeter.greet(firstName);

        assertThat(greeting).isEqualTo("Hello, " + firstName + "!");

        verify(visitStore).put(eq(firstName), any(Visit.class));
    }

    @Test
    void shouldGreetSecondTime() {
        when(visitStore.find(firstName)).thenReturn(Optional.of(new Visit(1)));

        final String greeting = greeter.greet(firstName);

        assertThat(greeting).isEqualTo("Welcome back, " + firstName + "!");

        verifyNoNewGuestAdded();
    }

    private void verifyNoNewGuestAdded() {
        verify(visitStore, never()).put(any(), any());
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 23, 25})
    void shouldGreet(int currentVisits) {
        when(visitStore.find(firstName)).thenReturn(Optional.of(new Visit(currentVisits)));

        final String greeting = greeter.greet(firstName);

        assertThat(greeting).isEqualTo("Hello my good friend, " + firstName + "!");

        verifyNoNewGuestAdded();
    }

    @Test
    void shouldGreetTwentyFiveTime() {
        when(visitStore.find(firstName)).thenReturn(Optional.of(new Visit(24)));

        final String greeting = greeter.greet(firstName);

        assertThat(greeting).isEqualTo("Hello my good friend, " + firstName + "! Congrats! You are now a platinum guest!");

        verifyNoNewGuestAdded();
    }
}