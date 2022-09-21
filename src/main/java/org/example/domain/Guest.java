package org.example.domain;

import java.io.Serial;
import java.io.Serializable;


public record Guest(String firstName) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

}

