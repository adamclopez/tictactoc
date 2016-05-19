import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;


/**
 * JFrame to hold Board
 */
public class TicTacToe extends JFrame {

    //indicate who's turn it is
    private char whoseTurn = 'X';
    //game over?
    private boolean gameover = false;

    //3x3 array of cells
    private Cell[][] cells = new Cell[3][3];

    //status label -- starts at x.
    private JLabel statusLabel = new JLabel("X's turn");

    //button for starting a new game
    private JButton newGameButton = new JButton("New Game");

    //panel for holding cells
    private JPanel panel = new JPanel(new GridLayout(3,3,0,0));


    /**
     * Constructor
     *
     */
    public TicTacToe(){
        //Gives the new game button an action listener.
        newGameButton.addActionListener (e -> newGame());

        //give them borders
        panel.setBorder(new LineBorder(Color.DARK_GRAY, 1));
        statusLabel.setBorder(new LineBorder(Color.YELLOW, 1));

        newGame();

        add(panel, BorderLayout.CENTER);
        add(newGameButton, BorderLayout.NORTH);
        add(statusLabel, BorderLayout.SOUTH);
    }

    /**
     * Resets the board and starts a new game
     */
    private void newGame() {
        gameover = false;
        whoseTurn = 'X';

        //remove any previous game
        panel.removeAll();

        //fills the board
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j < 3; j++) {
                //adds a new cell to the panel and initialize it
                panel.add(cells[i][j] = new Cell());
            }
        }

        //revalidate the board
        panel.revalidate();

        //new game button
        newGameButton.setVisible(true);
    }

    /**
     * Determine if game board is full.
     * @return True/False if game board is full.
     */

    public boolean isFull() {

        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j < 3; j++) {
                //if token is a space, the spot is empty.
                if (cells[i][j].getToken() == ' ') {
                    //if any spot is empty, board is not full
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Determines if a given token has won.
     * @param token to test for winning
     * @return True/False if the token has won.
     */
    public boolean isWon(char token) {

        //rows
        for (int i = 0; i < 3; i++) {
            if (    (cells[i][0].getToken() == token) &&
                    (cells[i][1].getToken() == token) &&
                    (cells[i][2].getToken() == token)
                    )
            {
                return true;
            }
        }
        //columns
        for (int j = 0; j < 3; j++) {
            if (    (cells[0][j].getToken() == token) &&
                    (cells[1][j].getToken() == token) &&
                    (cells[2][j].getToken() == token)
                    )
            {
                return true;
            }
        }

        //diagonal 1
        if  (    (cells[0][0].getToken() == token) &&
                (cells[1][1].getToken() == token) &&
                (cells[2][2].getToken() == token)
                )
        {
            return true;
        }

        //diagonal 2
        if  (    (cells[0][2].getToken() == token) &&
                (cells[1][1].getToken() == token) &&
                (cells[2][0].getToken() == token)
                )
        {
            return true;
        }

        return false;
    }

    /**
     * Cell class.
     */
    public class Cell extends JPanel {
        //Token of this cell
        private char token = ' ';

        /**
         * Constructor
         */
        public Cell() {
            setBorder(new LineBorder(Color.black, 1));

            addMouseListener(new MouseListener());
        }

        /**
         * Returns the token of the cell
         * @return The token value of the cell
         */
        public char getToken() {
            return token;
        }

        /**
         * Sets the token, repaints the cell
         * param cells desired new token
         */
        public void setToken(char newtoken) {
            token = newtoken;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;

            g2.setStroke(new BasicStroke(5));

            if (token == 'X') {
                g2.setPaint(Color.BLACK);
                g2.drawLine(10,10, getWidth() -10, getHeight() - 10);
                g2.drawLine(getWidth()- 10, 10, 10, getHeight() - 10);

            } else if (token == 'O') {
                g2.setPaint(Color.ORANGE);
                g2.drawOval(10,10,getWidth() -20, getHeight() - 20);
            }
        }

        /**
         * Mouse listener for each cell.
         * Game logic is handled in here.
         * Determines win condition after each turn is played
         */
        private class MouseListener extends MouseAdapter {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (gameover)
                    return;

                if (token == ' ' && whoseTurn != ' ') {
                    setToken (whoseTurn);
                } else {
                    return;
                }

                //check game status
                if (isWon(whoseTurn)) {
                    statusLabel.setText(whoseTurn + " won! Game over!");
                    whoseTurn = ' ';

                    gameover = true;

                    //Displays a message to the user informing them game is over.
                    JOptionPane.showMessageDialog(null, statusLabel.getText(), "Game Over!",
                            JOptionPane.INFORMATION_MESSAGE);

                    //Displays the new game button
                    newGameButton.setVisible(true);
                }
                else if (isFull()){

                    statusLabel.setText("Tie Game! Start a New Game!");
                    gameover = true;

                    //message to the user that it's game over.
                    JOptionPane.showMessageDialog(null, statusLabel.getText(), "Game Over!",
                            JOptionPane.INFORMATION_MESSAGE);

                    newGameButton.setVisible(true);
                }
                else {
                    //Checks whose turn it is
                    whoseTurn = (whoseTurn == 'X') ? 'O' : 'X';

                    statusLabel.setText(whoseTurn + "'s turn.");
                }

            }
        } //end MouseListener class
    } //end Cell class
} //end TicTacToe class