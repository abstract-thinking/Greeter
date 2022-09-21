package org.example;

import com.github.javafaker.Faker;
import org.example.control.Greeter;
import org.example.domain.Guest;
import org.example.persistence.GuestStore;
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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GreeterTest {

    private String firstName;

    @Mock
    private GuestStore guestStore;

    @InjectMocks
    private Greeter greeter;

    @BeforeEach
    public void generateFirstName() {
        firstName = new Faker().name().firstName();
    }

    @Test
    void shouldGreetFirstTime() {
        when(guestStore.find(firstName)).thenReturn(Optional.empty());

        final String greeting = greeter.greet(firstName);

        assertThat(greeting).isEqualTo("Hello, " + firstName + "!");

        verify(guestStore).add(any());
    }

    @Test
    void shouldGreetSecondTime() {
        when(guestStore.find(firstName)).thenReturn(Optional.of(new Guest(firstName, 1)));

        final String greeting = greeter.greet(firstName);

        assertThat(greeting).isEqualTo("Welcome back, " + firstName + "!");

        verifyNoNewGuestAdded();
    }

    private void verifyNoNewGuestAdded() {
        verify(guestStore, never()).add(any());
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 23, 25})
    void shouldGreet(int currentVisits) {
        when(guestStore.find(firstName)).thenReturn(Optional.of(new Guest(firstName, currentVisits)));

        final String greeting = greeter.greet(firstName);

        assertThat(greeting).isEqualTo("Hello my good friend, " + firstName + "!");

        verifyNoNewGuestAdded();
    }

    @Test
    void shouldGreetTwentyFiveTime() {
        when(guestStore.find(firstName)).thenReturn(Optional.of(new Guest(firstName, 24)));

        final String greeting = greeter.greet(firstName);

        assertThat(greeting).isEqualTo("Hello my good friend, " + firstName + "! Congrats! You are now a platinum guest!");

        verifyNoNewGuestAdded();
    }
}