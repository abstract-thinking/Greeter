package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.control.Greeter;
import org.example.domain.Guest;
import org.example.persistence.VisitStore;

import java.util.Scanner;

@Slf4j
public class GuestApplication {

    public static void main(String[] args) {
        Greeter greeter = new Greeter(new VisitStore());
        Runtime.getRuntime().addShutdownHook(new Thread(greeter::save));

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                log.info("What's your name?");
                String greeting = greeter.greet(new Guest(scanner.next()));
                log.info((greeting));
            }
        }
    }
}