package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a set of Logs
public class Record implements Writable {

    private String name;
    private ArrayList<Log> record;

    // Create a new record with an empty list of logs
    public Record() {
        record = new ArrayList<>();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("record", recordToJson());
        return json;
    }

    // EFFECTS: returns things in this record as a JSON array
    private JSONArray recordToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Log l : record) {
            jsonArray.put(l.toJson());
        }
        return jsonArray;
    }

    public boolean addLog(Log log) {
        return record.add(log);
    }

    public Log getLog(int i) {
        return record.get(i);
    }

    public Log getMostRecentLog() {
        return record.get(record.size() - 1);
    }

    public boolean isEmpty() {
        return record.isEmpty();
    }

    public int getLength() {
        return record.size();
    }
}
