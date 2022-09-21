package org.example;

import com.github.javafaker.Faker;
import org.example.control.Greeter;
import org.example.domain.Guest;
import org.example.persistence.GuestStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    public void shouldGreetFirstTime() {
        when(guestStore.find(firstName)).thenReturn(Optional.empty());

        final String greeting = greeter.createGreeting(firstName);

        assertThat(greeting).isEqualTo("Hello, " + firstName + "!");

        verify(guestStore).add(any());
    }

    @Test
    public void shouldGreetSecondTime() {
        when(guestStore.find(firstName)).thenReturn(Optional.of(new Guest(firstName, 1)));

        final String greeting = greeter.createGreeting(firstName);

        assertThat(greeting).isEqualTo("Welcome back, " + firstName + "!");

        verify(guestStore, never()).add(any());
    }

    @Test
    public void shouldGreetThirdTime() {
        when(guestStore.find(firstName)).thenReturn(Optional.of(new Guest(firstName, 2)));

        final String greeting = greeter.createGreeting(firstName);

        assertThat(greeting).isEqualTo("Hello my good friend, " + firstName + "!");

        verify(guestStore, never()).add(any());
    }

    @Test
    public void shouldGreetTwentyFourTime() {
        when(guestStore.find(firstName)).thenReturn(Optional.of(new Guest(firstName, 23)));

        final String greeting = greeter.createGreeting(firstName);

        assertThat(greeting).isEqualTo("Hello my good friend, "  + firstName + "!");

        verify(guestStore, never()).add(any());
    }

    @Test
    public void shouldGreetTwentyFiveTime() {
        when(guestStore.find(firstName)).thenReturn(Optional.of(new Guest(firstName, 24)));

        final String greeting = greeter.createGreeting(firstName);

        assertThat(greeting).isEqualTo("Hello my good friend, " + firstName + "! Congrats! You are now a platinum guest!");

        verify(guestStore, never()).add(any());
    }

    @Test
    public void shouldGreetTwentySixTime() {
        when(guestStore.find(firstName)).thenReturn(Optional.of(new Guest(firstName, 25)));

        final String greeting = greeter.createGreeting(firstName);

        assertThat(greeting).isEqualTo("Hello my good friend, " + firstName + "!");

        verify(guestStore, never()).add(any());
    }
}