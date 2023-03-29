package ui;

import model.*;
import persistence.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUI {
    Record record = new Record();
    private static final String JSON_STORE = "./data/record.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public GUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        // Frame
        JFrame frame = new JFrame("StatsApp");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Text-field for new category
        JTextField textFieldNewCategory = new JTextField();
        textFieldNewCategory.setBounds(200, 150, 200, 30);
        frame.add(textFieldNewCategory);

        // Label for new category
        JLabel labelCategory = new JLabel();
        labelCategory.setBounds(100, 40, 200, 30);
        frame.add(labelCategory);

        // Button for new category
        JButton buttonNewCategory = new JButton("Make New Category");
        buttonNewCategory.setBounds(200, 200, 200, 30);

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

        // Button for new log
        JButton buttonNewLog = new JButton("New Log");
        buttonNewLog.setBounds(200, 300, 200, 30);

        // Add an ActionListener to the button
        buttonNewLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Log log = new Log();
                record.addLog(log);
            }
        });

        // Button to display all logs
        JButton buttonDisplay = new JButton("Display");
        buttonDisplay.setBounds(200, 400, 200, 30);

        // Label for this display button
        JLabel labelLogs = new JLabel();
        labelLogs.setBounds(75, 110, 400, 400);

        buttonDisplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        });

        // Text-field for adding to category value
        JTextField textFieldAddCategoryValue = new JTextField();
        textFieldAddCategoryValue.setBounds(450, 150, 75, 30);
        frame.add(textFieldAddCategoryValue);

        // Button for adding to category value
        JButton buttonAddCategoryValue = new JButton("Add");
        buttonAddCategoryValue.setBounds(450, 200, 75, 30);

        // Add an ActionListener to the button
        buttonAddCategoryValue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double userInput = Double.parseDouble(textFieldAddCategoryValue.getText());
                record.getMostRecentLog().getLast().addValue(userInput);
            }
        });

        // Text-field for subtracting from category value
        JTextField textFieldSubCategoryValue = new JTextField();
        textFieldSubCategoryValue.setBounds(550, 150, 75, 30);
        frame.add(textFieldSubCategoryValue);

        // Button for subtracting category value
        JButton buttonSubCategoryValue = new JButton("Sub");
        buttonSubCategoryValue.setBounds(550, 200, 75, 30);

        // Add an ActionListener to the button
        buttonSubCategoryValue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double userInput = Double.parseDouble(textFieldSubCategoryValue.getText());
                record.getMostRecentLog().getLast().subValue(userInput);
            }
        });

        // Text-field for setting category value
        JTextField textFieldSetCategoryValue = new JTextField();
        textFieldSetCategoryValue.setBounds(650, 150, 75, 30);
        frame.add(textFieldSetCategoryValue);

        // Button for setting category value
        JButton buttonSetCategoryValue = new JButton("Set");
        buttonSetCategoryValue.setBounds(650, 200, 75, 30);

        // Add an ActionListener to the button
        buttonSetCategoryValue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double userInput = Double.parseDouble(textFieldSetCategoryValue.getText());
                record.getMostRecentLog().getLast().setValue(userInput);
            }
        });

        createStatCircle(frame);

        // Button for saving
        JButton buttonSave = new JButton("Save Data");
        buttonSave.setBounds(200, 500, 200, 30);

        // Add an ActionListener to the button
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveJsonRecord();
            }
        });

        // Button for loading
        JButton buttonLoad = new JButton("Load Data");
        buttonLoad.setBounds(200, 600, 200, 30);

        // Add an ActionListener to the button
        buttonLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadRecord();
            }
        });

        frame.add(buttonNewCategory);
        frame.add(buttonNewLog);
        frame.add(buttonDisplay);
        frame.add(buttonAddCategoryValue);
        frame.add(buttonSubCategoryValue);
        frame.add(buttonSetCategoryValue);
        frame.add(buttonSave);
        frame.add(buttonLoad);
        frame.setVisible(true);
    }

    private void createStatCircle(JFrame frame) {
        StatCircle circle = new StatCircle();
        frame.add(circle, BorderLayout.CENTER);
        // THIS IS WHY IT NEVER SHOWED UP
        circle.setBounds(550, 300, 250, 250);
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
