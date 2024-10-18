import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3]; // 3x3 grid of buttons
    private char currentPlayer = 'X'; // Track current player

    public TicTacToeGUI() {
        // Set up the JFrame
        setTitle("Tic Tac Toe Challenge");
        setSize(400, 400);
        setLayout(new GridLayout(3, 3)); // Grid layout for 3x3 Tic Tac Toe grid
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize buttons and add them to the JFrame
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton(""); // Empty button
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 100)); // Set font size
                buttons[i][j].setFocusPainted(false); // Remove focus border
                buttons[i][j].addActionListener(this); // Add action listener to button
                add(buttons[i][j]); // Add button to the JFrame
            }
        }

        setVisible(true); // Make the JFrame visible
    }

    // Handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        // If the button is already clicked, return
        if (!clickedButton.getText().equals("")) {
            return;
        }

        // Set the button text to the current player's symbol (X or O)
        clickedButton.setText(String.valueOf(currentPlayer));

        // Check if the current player has won
        if (isWinner()) {
            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
            resetBoard();
        } else if (isDraw()) {
            JOptionPane.showMessageDialog(this, "The game is a draw!");
            resetBoard();
        } else {
            // Switch player after each move
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    // Check for a win condition
    public boolean isWinner() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i][2].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
            // Check columns
            if (buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][i].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        // Check diagonals
        if (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][2].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }
        if (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }
        return false;
    }

    // Check if the game is a draw (no empty spaces left)
    public boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false; // There's at least one empty space, so it's not a draw
                }
            }
        }
        return true; // No empty spaces left, it's a draw
    }

    // Reset the game board
    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(""); // Clear all buttons
            }
        }
        currentPlayer = 'X'; // Reset current player to X
    }

    public static void main(String[] args) {
        new TicTacToeGUI(); // Start the game
    }
}
