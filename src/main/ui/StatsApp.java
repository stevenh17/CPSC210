package ui;

import model.*;

import java.util.Scanner;

public class StatsApp {

    private Log categoryList;
    private Scanner inputString;
    private Scanner inputDouble;
    private Log log;
    private Record record = new Record();

    @SuppressWarnings("methodlength")
    // EFFECTS: runs the application
    public StatsApp() {
        if (record.getLength() == 0) {
            createCategories();
        }
        // wonder why this if statement is not properly executing? confident that getLength should return 1 based on
        // the print statement below on line 38?
        boolean loopContinues = true;
        while (loopContinues) {
            displayCategories();
            String command = inputString.next().toLowerCase();
            for (int i = 0; i < categoryList.getLength(); i++) {
                if (command.equals(categoryList.get(i).getName().toLowerCase())) {
                    editCategory(categoryList.get(i));
                }
            }
            if (command.equals("r")) {
                readLog(categoryList);
            }
            if (command.equals("s")) {
                saveRecord(categoryList);
                loopContinues = false;
                System.out.println(record.getLength());
                new StatsApp();
            }
            if (command.equals("q")) {
                loopContinues = false;
            }
        }
    }

    private void subsequentApp() {
    }

    // MODIFIES: this
    // EFFECTS: initializes and instantiates categories
    private void createCategories() {
        inputString = new Scanner(System.in); // not sure why I have to do this NullPointerException
        System.out.println("Enter the categories you would like to track");
        Boolean keepAsking = true;
        categoryList = new Log();

        while (keepAsking) {
            String input = inputString.next();
            System.out.println("Entered: " + input);
            categoryList.add(new Category(input, 0));

            if (!input.matches("[a-zA-Z]*")) {
                categoryList.removeLast();
                keepAsking = false;
            }
        }
    }

    // EFFECTS: displays list of categories that can be edited
    private void displayCategories() {
        System.out.println("\nSelect categories to edit:");
        categoryList.displayCurrentLog();
        System.out.println("r -> read log");
        System.out.println("s -> save log");
        System.out.println("q -> quit");
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
        inputString = new Scanner(System.in); // not sure why I have to do this NullPointerException
        displayEditOptions();
        String input = inputString.next();

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

    public void saveRecord(Log log) {
        record.add(log);
    }

}
