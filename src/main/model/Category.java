package model;

import org.json.JSONObject;

// Represents variable that the user tracks
public class Category {
    private String name;           // name of the category
    private double value;          // numerical value of a category

    // REQUIRES: non-zero categoryName length and categoryValue >=  0
    // EFFECT: name is set to categoryName and value is set to categoryValue
    public Category(String categoryName, double categoryValue) {
        name = categoryName;
        if (categoryValue >= 0) {
            value = categoryValue;
        } else {
            value = 0;
        }
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    // MODIFIES: this
    // EFFECT: categoryValue is set to given value and returned
    public double setValue(double setValue) {
        this.value = setValue;
        return value;
    }

    // MODIFIES: this
    // EFFECT: categoryValue decreases by addValue and returns value
    public double subValue(double subValue) {
        this.value = value - subValue;
        return value;
    }

    // MODIFIES: this
    // EFFECT: categoryValue increases by addValue and returns value
    public double addValue(double addValue) {
        this.value = value + addValue;
        return value;
    }

    // MODIFIES: this
    // EFFECT: creates a JSON version of category
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("value", value);
        return json;
    }
}

