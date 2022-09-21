package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.control.GreeterCreator;

import java.util.Scanner;

 @Slf4j
public class GuestApplication {

    public static void main(String[] args) {
        GreeterCreator greeterCreator = new GreeterCreator();
        Runtime.getRuntime().addShutdownHook(new Thread(greeterCreator::save));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            log.info("What's your name?");
            String greeting = greeterCreator.createGreeting(scanner.next());
            log.info((greeting));
        }
    }
}