package model;

import java.util.ArrayList;

// Represents a set of Logs
public class Record {

    ArrayList<Log> record;

    // Create a new record
    public Record() {
        record = new ArrayList<>();
    }

    public boolean add(Log log) {
        return record.add(log);
    }

    public boolean isEmpty() {
        return record.isEmpty();
    }

    public int getLength() {
        return record.size();
    }
}
