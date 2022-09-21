package org.example;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        VisitorGreeter visitorGreeter = new VisitorGreeter();

        Runtime.getRuntime().addShutdownHook(new Thread(visitorGreeter::save));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("What's your name?");
            String greeting = visitorGreeter.doGreeting(scanner.next());
            System.out.println(greeting);
        }
    }
}