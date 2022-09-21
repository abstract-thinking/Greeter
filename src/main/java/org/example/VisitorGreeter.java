package org.example;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class VisitorGreeter {

    private final Set<Visitor> visitors;

    public VisitorGreeter() {
        visitors = VisitorSaver.load();
    }

    public String doGreeting(String name) {
        Optional<Visitor> optionalVisitor = visitors.stream()
                .filter(v -> v.getName().equalsIgnoreCase(name))
                .findFirst();

        if (optionalVisitor.isPresent()) {
            final Visitor visitor = optionalVisitor.get();
            visitor.newVisit();

            final int visits = visitor.getVisits();
            if (visits == 2) {
                return "Welcome back, " + name + "!";
            } else {
                String greeting = "Hello my good friend, " + name + "!";
                if (visits == 25) {
                    greeting += " Congrats! You are now a platinum guest!";
                }

                return greeting;
            }

        } else {
            visitors.add(new Visitor(name));
            return "Hello, " + name + "!";
        }
    }

    public void save() {
        VisitorSaver.save(visitors);
    }
}
