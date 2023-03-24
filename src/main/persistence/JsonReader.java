package persistence;

import model.Category;
import model.Log;
import model.Record;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads record from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads record from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Record read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRecord(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses record from JSON object and returns it
    private Record parseRecord(JSONObject jsonObject) {
        // String name = jsonObject.getString("name");
        Record r = new Record();
        addTheRecord(r, jsonObject);
        return r;
    }

    // MODIFIES: r
    // EFFECTS: parses records from JSON object and adds them to record
    private void addTheRecord(Record r, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("record");
        for (Object json : jsonArray) {
            JSONObject theNextLog = (JSONObject) json;
            addTheNextLogs(r, theNextLog);
        }
    }

    // MODIFIES: r
    // EFFECTS: parses logs from JSON object and adds it to record
    private void addTheNextLogs(Record r, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("log");
        Log log = new Log();
        for (Object json : jsonArray) {
            // now looking at each category
            JSONObject theNextCategory = (JSONObject) json;
            addTheNextCategory(log, theNextCategory);
        }
        r.addLog(log);
    }

    // MODIFIES: r
    // EFFECTS: parses category from JSON object and adds it to log
    private void addTheNextCategory(Log r, JSONObject theNextCategory) {
        String name = theNextCategory.getString("name");
        Double value = theNextCategory.getDouble("value");
        Category category = new Category(name, value);
        r.add(category);

    }
}
