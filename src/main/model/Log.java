package model;

import java.util.LinkedList;

// Represents a set of categories
public class Log {
    private static int nextLog = 0;        // id of next log created
    private int id;                        // current log id

    LinkedList<Category> log;

    // EFFECTS: creates new log
    public Log(Category one, Category two, Category three, Category four, Category five) {
        id = nextLog + 1;
        nextLog++;
        log = new LinkedList<>();
        log.add(one);
        log.add(two);
        log.add(three);
        log.add(four);
        log.add(five);
    }

    public int getID() {
        return id;
    }

    public int getLength() {
        return log.size();
    }

    // Had to manually create a getter
    public Category get(int i) {
        return log.get(i);
    }
}
