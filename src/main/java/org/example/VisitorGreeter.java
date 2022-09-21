package org.example;

import java.util.Optional;
import java.util.Set;

public class VisitorGreeter {

    private final Set<Visitor> visitors;

    public VisitorGreeter() {
        visitors = VisitorsFileHandler.load();
    }

    public String doGreeting(String name) {
        final Visitor visitor = findVisitor(name);

        visitor.newVisit();

        return createGreeterMessage(visitor);
    }

    private Visitor findVisitor(String name) {
        Optional<Visitor> optionalVisitor = visitors.stream()
                .filter(v -> v.getName().equalsIgnoreCase(name))
                .findFirst();

        if (optionalVisitor.isEmpty()) {
            final Visitor visitor = new Visitor(name);
            visitors.add(visitor);
            return visitor;
        } else {
            return optionalVisitor.get();
        }
    }

    private static String createGreeterMessage(Visitor visitor) {
        if (visitor.isFirstVisit()) {
            return "Hello, " + visitor.getName() + "!";
        } else if (visitor.isSecondVisit()) {
            return "Welcome back, " + visitor.getName() + "!";
        } else {
            String greeting = "Hello my good friend, " + visitor.getName() + "!";
            if (visitor.isPlatinumVisit()) {
                greeting += " Congrats! You are now a platinum guest!";
            }

            return greeting;
        }
    }

    public void save() {
        VisitorsFileHandler.save(visitors);
    }
}
