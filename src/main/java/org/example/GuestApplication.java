package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.control.Greeter;
import org.example.persistence.GuestStore;

import java.util.Scanner;

 @Slf4j
public class GuestApplication {

    public static void main(String[] args) {
        Greeter greeter = new Greeter(new GuestStore());
        Runtime.getRuntime().addShutdownHook(new Thread(greeter::save));

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                log.info("What's your name?");
                String greeting = greeter.createGreeting(scanner.next());
                log.info((greeting));
            }
        }
    }
}