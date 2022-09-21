package org.example;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Data
public class Visitor implements Serializable {

    private final String name;

    private int visits;

    public Visitor(String name) {
        this.name = name;
        this.visits = 1;
    }

    public void newVisit() {
        ++visits;
    }
}
