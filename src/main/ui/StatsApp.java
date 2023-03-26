package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class StatsApp {
    private Scanner inputString;
    private Scanner inputDouble;
    Record record = new Record();
    private static final String JSON_STORE = "./data/record.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs record and runs application
    public StatsApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runStatsApp();
    }

    // MODIFIES: this
    // EFFECTS: runs the application and takes in user input
    private void runStatsApp() {
        boolean loopContinues = true;
        while (loopContinues) {
            displayCategories();
            String command = getCommand();
            editCategoryIfChosen(command);
            if (command.equals("c")) {
                createCategories();
            }
            if (command.equals("r")) {
                readLog(record.getMostRecentLog());
            }
            if (command.equals("s")) {
                saveJsonRecord();
            }
            if (command.equals("l")) {
                loadRecord();
            }
            if (command.equals("q")) {
                loopContinues = false;
            }
        }
    }

    private void editCategoryIfChosen(String command) {
        if (!record.isEmpty()) {
            for (int i = 0; i < record.getMostRecentLog().getLength(); i++) {
                if (command.equals(record.getMostRecentLog().get(i).getName().toLowerCase())) {
                    editCategory(record.getMostRecentLog().get(i));
                }
            }
        }
    }

    private String getCommand() {
        inputString = new Scanner(System.in);
        String command = inputString.next().toLowerCase();
        return command;
    }

    // MODIFIES: this
    // EFFECTS: initializes and instantiates categories
    private void createCategories() {
        System.out.println("Enter the categories you would like to track");
        Boolean keepAsking = true;

        // savedRecord is all of its past logs
        if (!record.isEmpty()) {
            loadRecord();
            Record savedRecord = record;
            Log mostRecentLog = savedRecord.getMostRecentLog();
            record.addLog(new Log());
            record.addLog(mostRecentLog);
        } else {
            record.addLog(new Log());
        }

        while (keepAsking) {
            String input = inputString.next();
            System.out.println("Entered: " + input);
            record.getMostRecentLog().add(new Category(input, 0));

            if (!input.matches("[a-zA-Z]*")) {
                record.getMostRecentLog().removeLast();
                keepAsking = false;
            }
        }
    }

    // EFFECTS: displays list of categories that can be edited
    private void displayCategories() {
        System.out.println("\nSelect categories to edit:");
        if (!record.isEmpty()) {
            displayLog(record.getMostRecentLog());
        }
        System.out.println("c -> create new categories");
        System.out.println("r -> read log");
        System.out.println("s -> save log");
        System.out.println("l -> load log");
        System.out.println("q -> quit");
    }

    // EFFECTS: print the names of all categories within log
    public void displayLog(Log log) {
        for (int i = 0; i < log.getLength(); i++) {
            System.out.println(log.get(i).getName());
        }
    }

    // EFFECTS: displays list of edit options
    private void displayEditOptions() {
        System.out.println("\nSelect edit options:");
        System.out.println("\ta -> Add");
        System.out.println("\tb -> Subtract");
        System.out.println("\tc -> Set Value");
    }

    // MODIFIES: this
    // EFFECT: chosen category's value is changed
    private void editCategory(Category category) {
        displayEditOptions();
        String input = getCommand();
        if (input.equals("a")) {
            doAddValue(category);
        } else if (input.equals("b")) {
            doSubValue(category);
        } else if (input.equals("c")) {
            doSetValue(category);
        } else {
            System.out.println("Invalid input");
        }
    }

    // MODIFIES: this
    // EFFECT: add to the given category
    private void doAddValue(Category category) {
        System.out.println("Input amount to add");
        inputDouble = new Scanner(System.in); // not sure why I have to do this
        double amount = inputDouble.nextDouble();
        System.out.println(category.getName() + ": " + category.addValue(amount));
    }

    // MODIFIES: this
    // EFFECT: minus from the given category
    private void doSubValue(Category category) {
        System.out.println("Input amount to subtract");
        inputDouble = new Scanner(System.in); // not sure why I have to do this
        double amount = inputDouble.nextDouble();
        System.out.println(category.getName() + ": " + category.subValue(amount));
    }

    // MODIFIES: this
    // EFFECT: set the given category's value
    private void doSetValue(Category category) {
        System.out.println("Input amount to set to");
        inputDouble = new Scanner(System.in); // not sure why I have to do this
        double amount = inputDouble.nextDouble();
        System.out.println(category.getName() + ": " + category.setValue(amount));
    }

    // EFFECTS: prints out each category within a log
    public void readLog(Log log) {
        System.out.println("Log " + log.getID() + ":\n");
        for (int i = 0; i < log.getLength(); i++) {
            System.out.println(log.get(i).getName() + ": " + log.get(i).getValue());
        }
    }

    // EFFECTS: saves the record to file
    private void saveJsonRecord() {
        try {
            jsonWriter.open();
            jsonWriter.write(record);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads record from file
    private void loadRecord() {
        try {
            record = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
