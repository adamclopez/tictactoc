import javax.swing.*;
/**CIT63 Final Project for Adam Lopez.
 */
public class main {

    public static void main(String[] args) {

        JFrame TicTacToeGUI = new TicTacToe();
        TicTacToeGUI.setTitle("Tic Tac Toe");
        TicTacToeGUI.setSize(600,600);
        TicTacToeGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TicTacToeGUI.setLocationRelativeTo(null);
        TicTacToeGUI.setVisible(true);
    }
}
