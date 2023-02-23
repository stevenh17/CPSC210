package model;

public class Category {
    private String name;           // name of the category
    private double value = 0;          // numerical value of a category

    // REQUIRES: non-zero categoryName length and categoryValue >=  0
    // EFFECT: name is set to categoryName and value is set to categoryValue
    public Category(String categoryName, double categoryValue) {
        name = categoryName;
        value = categoryValue;
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
        value = setValue;
        return value;
    }

    // MODIFIES: this
    // EFFECT: categoryValue decreases by addValue and returns value
    public double subValue(double subValue) {
        value -= subValue;
        return value;
    }

    // MODIFIES: this
    // EFFECT: categoryValue increases by addValue and returns value
    public double addValue(double addValue) {
        value += addValue;
        return value;
    }


}

