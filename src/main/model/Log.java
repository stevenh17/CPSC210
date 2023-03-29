package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;
import java.util.List;

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

    public LinkedList<Category> getAllCategories() {
        return log;
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

    // EFFECT: removes the last category in a log
    public void removeLast() {
        log.removeLast();
    }

    // EFFECT: get last element in a log
    public Category getLast() { return log.getLast(); }

    // MODIFIES: this
    // EFFECTS: creates JSON version of log
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("log", logToJson());
        json.put("id", id);
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
