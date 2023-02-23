package model;

import java.util.LinkedList;

// Represents a set of categories
public class Log {
    private static int nextLog = 0;        // id of next log created
    private int id;                        // current log id

    LinkedList<Category> log;

    // EFFECTS: creates new log
    public Log(Category one, Category two, Category three, Category four, Category five) {
        id = nextLog++;
        log = new LinkedList<>();
        log.add(one);
        log.add(two);
        log.add(three);
        log.add(four);
        log.add(five);
    }

    public void readLog() {
        System.out.println("Log " + id + ":\n");
        for (Category c : log) {
            System.out.println(c.getName() + ": " + c.getValue());
        }
    }
}
