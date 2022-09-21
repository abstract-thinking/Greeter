package org.example.domain;

import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;


@EqualsAndHashCode
public class Visit implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int visits;

    public Visit() {
        this(0);
    }

    // Open for testing???
    public Visit(int visits) {
        if (visits < 0) {
            throw new IllegalArgumentException("visits can not be negative");
        }
        this.visits =  visits;
    }

    public void attended() {
        ++visits;
    }

    public boolean isFirst() {
        return visits == 1;
    }

    public boolean isSecond() {
        return visits == 2;
    }

    public boolean isPlatinum() {
        return visits == 25;
    }

}
