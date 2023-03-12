package model;

import java.util.LinkedList;

// Represents a set of categories
public class Log {
    private static int nextLog = 0;        // id of next log created
    private int id;                        // current log id

    LinkedList<Category> log;

    // EFFECTS: creates new log
    public Log() {
        id = nextLog + 1;
        nextLog++;
        log = new LinkedList<>();
    }

    public void displayCurrentLog() {
        for (Category i : log) {
            System.out.println(i.getName());
        }
    }

    public int getID() {
        return id;
    }

    public int getLength() {
        return log.size();
    }

    public Category get(int i) {
        return log.get(i);
    }

    public Boolean add(Category i) {
        return log.add(i);
    }

    public Category removeLast() {
        return log.removeLast();
    }
}
