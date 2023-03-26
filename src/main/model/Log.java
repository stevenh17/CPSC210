package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;

// Represents a set of categories
public class Log implements Writable {
    private static int nextLog = 0;        // id of next log created
    private int id;                        // current log id

    LinkedList<Category> log;

    // EFFECTS: creates new log
    public Log() {
        id = nextLog + 1;
        nextLog++;
        log = new LinkedList<>();
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

    public void removeLast() {
        log.removeLast();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("log", logToJson());
        json.put("id", id);

        // Not sure how but log id is being saved even though I don't see in my record.json

        return json;
    }

    // EFFECTS: returns things in this log as a JSON object
    private JSONArray logToJson() {
        JSONArray json = new JSONArray();
        for (Category c : log) {
            json.put(c.toJson());
        }
        return json;
    }
}
