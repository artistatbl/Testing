package ex2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private char currentPlayer;
    private boolean gameOver;

    public TicTacToeGame() {
        buttons = new JButton[3][3];
        currentPlayer = 'X';
        gameOver = false;

        initialiseUI();
    }

    private void initialiseUI() {
        setTitle("Tic-Tac-Tooe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 48));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        if (!buttonClicked.getText().equals("")) {
            // Introduce the bug: Allow overriding previous moves
            buttonClicked.setText(Character.toString(currentPlayer));
        } else {
            buttonClicked.setText(Character.toString(currentPlayer));

            if (checkForWin() || checkForTie()) {
                gameOver = true;
                return;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private boolean checkForWin() {
        // Check rows, columns, and diagonals for a winning combination
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(Character.toString(currentPlayer)) &&
                    buttons[i][1].getText().equals(Character.toString(currentPlayer)) &&
                    buttons[i][2].getText().equals(Character.toString(currentPlayer)) ||
                    buttons[0][i].getText().equals(Character.toString(currentPlayer)) &&
                            buttons[1][i].getText().equals(Character.toString(currentPlayer)) &&
                            buttons[2][i].getText().equals(Character.toString(currentPlayer))) {
                announceWinner(currentPlayer);
                return true;
            }
        }

        // Check diagonals
        if (buttons[0][0].getText().equals(Character.toString(currentPlayer)) &&
                buttons[1][1].getText().equals(Character.toString(currentPlayer)) &&
                buttons[2][2].getText().equals(Character.toString(currentPlayer)) ||
                buttons[0][2].getText().equals(Character.toString(currentPlayer)) &&
                        buttons[1][1].getText().equals(Character.toString(currentPlayer)) &&
                        buttons[2][0].getText().equals(Character.toString(currentPlayer))) {
            announceWinner(currentPlayer);
            return true;
        }

        return false;
    }

    private boolean checkForTie() {
        // Check if all cells are filled
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false; // Game is not tied, continue playing
                }
            }
        }

        // Announce a tie if all cells are filled
        announceTie();
        return true;
    }

    private void announceWinner(char winner) {
        JOptionPane.showMessageDialog(this, "Player " + winner + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    private void announceTie() {
        JOptionPane.showMessageDialog(this, "It's a tie!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new TicTacToeGame();
    }
}
