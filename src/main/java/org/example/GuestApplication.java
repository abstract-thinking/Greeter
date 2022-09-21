package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.control.Greeter;
import org.example.domain.Guest;
import org.example.persistence.VisitStore;

import java.util.Scanner;

@Slf4j
public class GuestApplication {

    public static void main(String[] args) {
        VisitStore visitStore = new VisitStore();
        Greeter greeter = new Greeter(visitStore);
        Runtime.getRuntime().addShutdownHook(new Thread(visitStore::save));

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                log.info("What's your name?");
                String greeting = greeter.greet(new Guest(scanner.next()));
                log.info((greeting));
            }
        }
    }
}