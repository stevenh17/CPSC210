package ui;

import model.*;

import java.util.Scanner;

public class StatsApp {
    private Category categoryOne;
    private Category categoryTwo;
    private Category categoryThree;
    private Category categoryFour;
    private Category categoryFive;

    private Scanner inputString;
    private Scanner inputDouble;

    // EFFECTS: runs the application
    @SuppressWarnings("methodlength")
    public StatsApp() {
        createCategories();
        boolean loopContinues = true;
        while (loopContinues) {
            displayCategories();
            String command = inputString.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                loopContinues = false;
            }
            if (command.equals("a")) {
                editCategory(categoryOne);
            }
            if (command.equals("b")) {
                editCategory(categoryTwo);
            }
            if (command.equals("c")) {
                editCategory(categoryThree);
            }
            if (command.equals("d")) {
                editCategory(categoryFour);
            }
            if (command.equals("e")) {
                editCategory(categoryFive);
            }
        }
        Log log = new Log(categoryOne, categoryTwo, categoryThree, categoryFour, categoryFive);
        readLog(log);
    }

    // MODIFIES: this
    // EFFECTS: initializes and instantiates categories
    private void createCategories() {
        inputString = new Scanner(System.in); // not sure why I have to do this NullPointerException
        System.out.println("Select 5 categories to record:\n-\n-\n-\n-\n-");
        String nameOne = inputString.next();
        categoryOne = new Category(nameOne, 0);
        String nameTwo = inputString.next();
        categoryTwo = new Category(nameTwo, 0);
        String nameThree = inputString.next();
        categoryThree = new Category(nameThree, 0);
        String nameFour = inputString.next();
        categoryFour = new Category(nameFour, 0);
        String nameFive = inputString.next();
        categoryFive = new Category(nameFive, 0);
    }

    // EFFECTS: displays list of categories that can be edited
    private void displayCategories() {
        System.out.println("\nSelect categories to edit:");
        System.out.println("\ta -> " + categoryOne.getName());
        System.out.println("\tb -> " + categoryTwo.getName());
        System.out.println("\tc -> " + categoryThree.getName());
        System.out.println("\td -> " + categoryFour.getName());
        System.out.println("\te -> " + categoryFive.getName());
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

    // EFFECT: add to the given category
    private void doAddValue(Category category) {
        System.out.println("Input amount to add");
        inputDouble = new Scanner(System.in); // not sure why I have to do this
        double amount = inputDouble.nextDouble();
        System.out.println(category.getName() + ": " + category.addValue(amount));
    }

    // EFFECT: minus from the given category
    private void doSubValue(Category category) {
        System.out.println("Input amount to subtract");
        inputDouble = new Scanner(System.in); // not sure why I have to do this
        double amount = inputDouble.nextDouble();
        System.out.println(category.getName() + ": " + category.subValue(amount));
    }

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

}
