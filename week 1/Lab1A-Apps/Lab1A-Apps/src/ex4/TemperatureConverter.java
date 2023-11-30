package ex4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class TemperatureConverter extends JFrame {
    private JTextField inputField;
    private JLabel resultLabel;
    private JComboBox<String> conversionType;

    public TemperatureConverter() {
        // Set up the JFrame
        setTitle("Temperature Converter");
        setSize(550, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create and add components
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel temperatureLabel = new JLabel("Temperature:");
        inputField = new JTextField();
        conversionType = new JComboBox<>(new String[]{"Fahrenheit to Celsius", "Celsius to Fahrenheit"});
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel("");

        inputPanel.add(temperatureLabel);
        inputPanel.add(inputField);
        inputPanel.add(new JLabel("Conversion Type:"));
        inputPanel.add(conversionType);
        inputPanel.add(convertButton);
        inputPanel.add(resultLabel);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });

        add(inputPanel, BorderLayout.NORTH);
    }

    private void convertTemperature() {

        double temperature = Double.parseDouble(inputField.getText());
        String selectedConversion = (String) conversionType.getSelectedItem();
        double result = 0.0;

        if (selectedConversion.equals("Fahrenheit to Celsius")) {
            result = (temperature - 32) * 5 / 9;

            result += 0.5;
            resultLabel.setText("Result: " + result + " Celsius");
        } else if (selectedConversion.equals("Celsius to Fahrenheit")) {
            result = (temperature * 9 / 5) + 32;

            result *= 0.95;
            resultLabel.setText("Result: " + result + " Fahrenheit");
        }

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TemperatureConverter().setVisible(true);
            }
        });
    }
}
