package org.example.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;


@EqualsAndHashCode
public class Guest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Getter
    private final String firstName;

    private int visits;

    public Guest(String firstName) {
        this.firstName = firstName;
        this.visits = 0;
    }

    // Open for testing???
    public Guest(String firstName, int visits) {
        this.firstName = firstName;
        this.visits = visits;
    }


    public void newVisit() {
        ++visits;
    }

    public boolean isFirstVisit() {
        return visits == 1;
    }

    public boolean isSecondVisit() {
        return visits == 2;
    }

    public boolean isPlatinumVisit() {
        return visits == 25;
    }

}
