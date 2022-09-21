package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;


@EqualsAndHashCode
public class Visitor implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Getter
    private final String name;

    private int visits;

    public Visitor(String name) {
        this.name = name;
        this.visits = 0;
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
