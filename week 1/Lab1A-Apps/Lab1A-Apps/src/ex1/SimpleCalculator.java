package ex1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame implements ActionListener {
    private JTextField textfield;
    private String operator;
    private double num1, num2, result;

    public SimpleCalculator() {
        setTitle("Simple Calculator");

        textfield = new JTextField();
        textfield.setPreferredSize(new Dimension(280, 60)); // Set preferred size for textfield

        String[] buttons = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "C", "0", "=", "+"};

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(this);
            button.setPreferredSize(new Dimension(60, 60)); // Set preferred size for buttons
            panel.add(button);
        }

        add(textfield, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        pack(); // Adjust the frame size based on preferred sizes of contained components
        setVisible(true);
    }


    public static void main(String[] args) {
        new SimpleCalculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ((command.charAt(0) >= '1' && command.charAt(0) <= '9') || command.charAt(0) == '.') {
            textfield.setText(textfield.getText() + command);
        } else if (command.charAt(0) == 'C') {
            textfield.setText("");
            num1 = num2 = result = 0;
            operator = "";
        } else if (command.charAt(0) == '=') {
            num2 = Double.parseDouble(textfield.getText());
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
            }
            textfield.setText(String.valueOf(result));
            num1 = result;
            operator = "";
        } else {
            if (!textfield.getText().isEmpty()) {
                operator = command;
                num1 = Double.parseDouble(textfield.getText());
                textfield.setText("");
            }
        }
    }
}
