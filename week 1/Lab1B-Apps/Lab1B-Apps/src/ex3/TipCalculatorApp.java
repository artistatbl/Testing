package ex3;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TipCalculatorApp extends JFrame {
    private JTextField billAmountTextField;
    private JTextField tipPercentageTextField;
    private JButton calculateButton;
    private JLabel resultLabel;
    private JLabel tipAmountLabel; // Added a label for displaying tip amount

    public TipCalculatorApp() {
        // Set up the JFrame
        setTitle("Tip Calculator");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2)); // Added a row for displaying tip amount

        // Initialize components
        billAmountTextField = new JTextField();
        tipPercentageTextField = new JTextField();
        calculateButton = new JButton("Calculate");
        resultLabel = new JLabel("Result: "); // Added initial text
        tipAmountLabel = new JLabel("Tip Amount: "); // Added initial text

        // Add components to the JFrame
        add(new JLabel("Bill Amount:"));
        add(billAmountTextField);
        add(new JLabel("Tip Percentage:"));
        add(tipPercentageTextField);
        add(new JLabel()); // Empty cell
        add(calculateButton);
        add(resultLabel);
        add(tipAmountLabel);

        // Add action listener to the Calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTip();
            }
        });
    }

    private void calculateTip() {
        try {
            // Get user inputs
            double billAmount = Double.parseDouble(billAmountTextField.getText());
            double tipPercentage = Double.parseDouble(tipPercentageTextField.getText());

            // Calculate the tip
            double tipAmount = billAmount * (tipPercentage / 100);

            // Display the result and tip amount
            resultLabel.setText("Result: " + billAmount); // Show bill amount
            tipAmountLabel.setText("Tip Amount: " + tipAmount);

        } catch (NumberFormatException ex) {
            // Handle invalid input
            resultLabel.setText("Result: Invalid input. Please enter valid numbers.");
            tipAmountLabel.setText("Tip Amount: "); // Clear tip amount label on error
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TipCalculatorApp().setVisible(true);
            }
        });
    }
}

