package ui;

import model.*;
import model.Event;
import persistence.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUI {
    private JButton buttonLoad;
    private JButton buttonSave;
    private JButton buttonSetCategoryValue;
    private JTextField textFieldSetCategoryValue;
    private JTextField textFieldSubCategoryValue;
    private JTextField textFieldAddCategoryValue;
    private JLabel labelLogs;
    private final int circleX = 500;
    private final int circleY = 300;
    private final int circleWidth = 250;
    private final int circleHeight = 250;

    private JButton buttonDisplay;
    private JButton buttonNewLog;
    private JButton buttonNewCategory;
    private JTextField textFieldNewCategory;
    private JFrame frame;

    Record record = new Record();
    private static final String JSON_STORE = "./data/record.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public GUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        createFrame();
        everythingNewCategory();
        everythingNewLog();

        createButtonDisplay();
        buttonDisplayAction(buttonDisplay, labelLogs);

        everythingChangeCategoryValue();

        createStatCircle(frame, circleX, circleY, circleWidth, circleHeight);

        createButtonSave();
        buttonSaveAction(buttonSave);

        createButtonLoad();
        buttonLoadAction(buttonLoad);

        frame.setVisible(true);
    }

    // EFFECTS: creates the text fields and buttons for changing category values and adds to frame
    private void everythingChangeCategoryValue() {
        createTextFieldAddCategoryValue();
        JButton buttonAddCategoryValue = createButtonAddCategoryValue();
        buttonAddCategoryValueAction(textFieldAddCategoryValue, buttonAddCategoryValue);

        createTextFieldSubCategoryValue();
        JButton buttonSubCategoryValue = createButtonSubCategoryValue();
        buttonSubCategoryValueAction(textFieldSubCategoryValue, buttonSubCategoryValue);

        createTextFieldSetCategoryValue();
        createButtonSetCategoryValue();
        buttonSetCategoryValueAction(textFieldSetCategoryValue, buttonSetCategoryValue);

        frame.add(buttonAddCategoryValue);
        frame.add(buttonSubCategoryValue);
        frame.add(buttonSetCategoryValue);
    }

    // EFFECTS: creates the text fields and buttons for add logs and adds to frame
    private void everythingNewLog() {
        createLabelLog();
        createButtonNewLog();
        buttonNewLogAction(buttonNewLog);
        frame.add(buttonNewLog);
    }

    // EFFECTS: creates the text fields and buttons for making a new category
    private void everythingNewCategory() {
        createTextFieldNewCategory();
        JLabel labelCategory = createLabelCategory();
        createButtonNewCategory();
        buttonNewCategoryAction(textFieldNewCategory, labelCategory, buttonNewCategory);
        frame.add(buttonNewCategory);
    }

    // EFFECTS: creates the button to load data
    private void createButtonLoad() {
        buttonLoad = new JButton("Load Data");
        buttonLoad.setBounds(200, 600, 200, 30);
        frame.add(buttonLoad);
    }

    // EFFECTS: creates the button to save data
    private void createButtonSave() {
        buttonSave = new JButton("Save Data");
        buttonSave.setBounds(200, 500, 200, 30);
        frame.add(buttonSave);
    }

    // EFFECTS: creates the button to set category value
    private void createButtonSetCategoryValue() {
        buttonSetCategoryValue = new JButton("Set");
        buttonSetCategoryValue.setBounds(650, 200, 75, 30);
    }

    // EFFECTS: creates the text field to set category value
    private void createTextFieldSetCategoryValue() {
        textFieldSetCategoryValue = new JTextField();
        textFieldSetCategoryValue.setBounds(650, 150, 75, 30);
        frame.add(textFieldSetCategoryValue);
    }

    // EFFECTS: creates the button to subtract from category value
    private JButton createButtonSubCategoryValue() {
        JButton buttonSubCategoryValue = new JButton("Sub");
        buttonSubCategoryValue.setBounds(550, 200, 75, 30);
        return buttonSubCategoryValue;
    }

    // EFFECTS: creates the text field to subtract from category value
    private void createTextFieldSubCategoryValue() {
        textFieldSubCategoryValue = new JTextField();
        textFieldSubCategoryValue.setBounds(550, 150, 75, 30);
        frame.add(textFieldSubCategoryValue);
    }

    // EFFECTS: creates the button to add to category value
    private JButton createButtonAddCategoryValue() {
        JButton buttonAddCategoryValue = new JButton("Add");
        buttonAddCategoryValue.setBounds(450, 200, 75, 30);
        return buttonAddCategoryValue;
    }

    // EFFECTS: creates the text field to add to category value
    private void createTextFieldAddCategoryValue() {
        textFieldAddCategoryValue = new JTextField();
        textFieldAddCategoryValue.setBounds(450, 150, 75, 30);
        frame.add(textFieldAddCategoryValue);
    }

    // EFFECTS: creates the label to display new logs
    private void createLabelLog() {
        labelLogs = new JLabel();
        labelLogs.setBounds(75, 110, 400, 400);
    }

    // EFFECTS: creates the button to display logs
    private void createButtonDisplay() {
        buttonDisplay = new JButton("Display");
        buttonDisplay.setBounds(200, 400, 200, 30);
        frame.add(buttonDisplay);
    }

    // EFFECTS: creates the button to create a new log
    private void createButtonNewLog() {
        buttonNewLog = new JButton("New Log");
        buttonNewLog.setBounds(200, 300, 200, 30);
    }

    // EFFECTS: creates the text field to make a new category
    private void createTextFieldNewCategory() {
        textFieldNewCategory = new JTextField();
        textFieldNewCategory.setBounds(200, 150, 200, 30);
        frame.add(textFieldNewCategory);
    }

    // EFFECTS: creates the label to make a new category
    private JLabel createLabelCategory() {
        JLabel labelCategory = new JLabel();
        labelCategory.setBounds(100, 40, 200, 30);
        frame.add(labelCategory);
        return labelCategory;
    }

    // EFFECTS: creates the frame
    private void createFrame() {
        frame = new JFrame("StatsApp");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.getDate());
                    System.out.println(event.getDescription());
                    System.out.println();
                }
            }
        });
        frame.setLayout(null);
    }

    // EFFECTS: creates the button for a new category
    private void createButtonNewCategory() {
        buttonNewCategory = new JButton("Make New Category");
        buttonNewCategory.setBounds(200, 200, 200, 30);
    }

    // EFFECTS: loads the data
    private void buttonLoadAction(JButton buttonLoad) {
        buttonLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadRecord();
            }
        });
    }

    // EFFECTS: saves the data
    private void buttonSaveAction(JButton buttonSave) {
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveJsonRecord();
            }
        });
    }

    // EFFECTS: ActionListener that sets category value
    private void buttonSetCategoryValueAction(JTextField textFieldSetCategoryValue, JButton buttonSetCategoryValue) {
        buttonSetCategoryValue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double userInput = Double.parseDouble(textFieldSetCategoryValue.getText());
                record.getMostRecentLog().getLast().setValue(userInput);
            }
        });
    }

    // EFFECTS: ActionListener that subtracts from category value
    private void buttonSubCategoryValueAction(JTextField textFieldSubCategoryValue, JButton buttonSubCategoryValue) {
        buttonSubCategoryValue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double userInput = Double.parseDouble(textFieldSubCategoryValue.getText());
                record.getMostRecentLog().getLast().subValue(userInput);
            }
        });
    }

    // EFFECTS: ActionListener that adds to category value
    private void buttonAddCategoryValueAction(JTextField textFieldAddCategoryValue, JButton buttonAddCategoryValue) {
        buttonAddCategoryValue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double userInput = Double.parseDouble(textFieldAddCategoryValue.getText());
                record.getMostRecentLog().getLast().addValue(userInput);
            }
        });
    }

    // EFFECTS: ActionListener that displays logs
    private void buttonDisplayAction(JButton buttonDisplay, JLabel labelLogs) {
        buttonDisplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateLogs(labelLogs);
                updateStats();

            }
        });
    }

    // EFFECTS: updates the list of logs created
    private void updateLogs(JLabel labelLogs) {
        // Label for logs
        frame.add(labelLogs);

        // String of all logs
        String logsTotal = "";

        // Loop through all logs and their categories
        for (Log log : record.getAllLogs()) {
            logsTotal += "Log " + log.getID() + ":<br>";
            for (Category category : log.getAllCategories()) {
                logsTotal += " " + category.getName() + ": " + category.getValue() + "<br>";
            }
        }

        // Update the label to show all logs
        labelLogs.setText("<html>" + logsTotal + "</html>");
    }

    // EFFECTS: displays logs around the stats circle
    private void updateStats() {
        double centerX = circleX + circleWidth / 2;
        double centerY = circleY + circleHeight / 2;
        double preventOverlap = 25;
        double[][] vertices = {
                {centerX, centerY - 125 - preventOverlap},
                {centerX + 121.25 + preventOverlap, centerY - 38.63 - preventOverlap},
                {centerX + 75 + preventOverlap, centerY + 101.62 + preventOverlap},
                {centerX - 75 - preventOverlap, centerY + 101.62 + preventOverlap},
                {centerX - 121.25 - preventOverlap, centerY - 38.63 - preventOverlap}
        };

        for (int i = 0; i < record.getMostRecentLog().getLength(); i++) {
            JLabel labelStats = new JLabel();
            labelStats.setText(record.getMostRecentLog().get(i).getName());
//            labelStats.setBounds(circleX - 50 * i, circleY - 50 * i, 30, 25);
            labelStats.setBounds((int) vertices[i][0], (int) vertices[i][1], 30, 25);
            frame.add(labelStats);
        }
        frame.revalidate();
        frame.repaint();
    }

    // EFFECTS: ActionListener that creates new log
    private void buttonNewLogAction(JButton buttonNewLog) {
        // Add an ActionListener to the button
        buttonNewLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Log log = new Log();
                record.addLog(log);
            }
        });
    }

    // EFFECTS: ActionListener that creates category
    private void buttonNewCategoryAction(JTextField textFieldNewCategory, JLabel labelCategory,
                                         JButton buttonNewCategory) {
        // Add an ActionListener to the button
        buttonNewCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the text entered by the user
                String userInput = textFieldNewCategory.getText();

                // Set the text of the label to the user input
                labelCategory.setText(userInput);

                // Create a category
                Category category = new Category(userInput, 0);
                record.getMostRecentLog().add(category);
            }
        });
    }

    // EFFECTS: creates a cirlce which is the stats circle
    private void createStatCircle(JFrame frame, int circleX, int circleY, int circleWidth, int circleHeight) {
        Circle circle = new Circle();
        frame.add(circle, BorderLayout.CENTER);
        circle.setBounds(circleX, circleY, circleWidth, circleHeight);
        // circle.setBackground(Color.WHITE);
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
