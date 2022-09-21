package org.example;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

 @Slf4j
public class Main {

    public static void main(String[] args) {
        VisitorGreeter visitorGreeter = new VisitorGreeter();
        Runtime.getRuntime().addShutdownHook(new Thread(visitorGreeter::save));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            log.info("What's your name?");
            String greeting = visitorGreeter.doGreeting(scanner.next());
            log.info((greeting));
        }
    }
}