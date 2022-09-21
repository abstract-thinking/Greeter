package org.example;

import com.github.javafaker.Faker;
import org.example.control.GreeterCreator;
import org.example.persistence.GuestStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

class GreeterCreatorTest {

    private String firstName;

    private GreeterCreator greeter;

    @BeforeEach
    public void setUp() {
        GuestStore.save(new HashSet<>());

        firstName = new Faker().name().firstName();

        greeter = new GreeterCreator();
    }

    @Test
    public void shouldGreetFirstTime() {
        String greeting = simulateVisits(1);

        assertThat(greeting).isEqualTo("Hello, " + firstName + "!");
    }


    private String simulateVisits(int visit) {
        String greeting = "";
        for (int i = 1; i <= visit; ++i) {
            greeting = greeter.createGreeting(firstName);
        }

        return greeting;
    }

    @Test
    public void shouldGreetSecondTime() {
        String greeting = simulateVisits(2);

        assertThat(greeting).isEqualTo("Welcome back, " + firstName + "!");
    }

    @Test
    public void shouldGreetThirdTime() {
        String greeting = simulateVisits(3);

        assertThat(greeting).isEqualTo("Hello my good friend, " + firstName + "!");
    }

    @Test
    public void shouldGreetTwentyFourTime() {
        String greeting = simulateVisits(24);

        assertThat(greeting).isEqualTo("Hello my good friend, "  + firstName + "!");
    }

    @Test
    public void shouldGreetTwentyFiveTime() {
        String greeting = simulateVisits(25);

        assertThat(greeting).isEqualTo("Hello my good friend, " + firstName + "! Congrats! You are now a platinum guest!");
    }

    @Test
    public void shouldGreetTwentySixTime() {
        String greeting = simulateVisits(26);

        assertThat(greeting).isEqualTo("Hello my good friend, " + firstName + "!");
    }
}