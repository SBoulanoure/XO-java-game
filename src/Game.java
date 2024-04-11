import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Game extends JPanel implements MouseListener {
    private char[][] board;
    private boolean gameOver;
    private boolean xWin;
    private boolean oWin;
    private boolean xTurn;

    public Game() {
        board = new char[3][3];
        gameOver = false;
        xWin = false;
        oWin = false;
        xTurn = true;
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        if (gameOver) {
            drawGameOver(g);
        }
    }

    private void drawBoard(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(200, 0, 200, 600);
        g.drawLine(400, 0, 400, 600);
        g.drawLine(0, 200, 600, 200);
        g.drawLine(0, 400, 600, 400);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                drawCell(g, i, j);
            }
        }
    }

    private void drawCell(Graphics g, int row, int col) {
        int x = col * 200;
        int y = row * 200;

        if (board[row][col] == 'X') {
            drawX(g, x, y);
        } else if (board[row][col] == 'O') {
            drawO(g, x, y);
        }
    }

    private void drawX(Graphics g, int x, int y) {
        g.setColor(Color.RED);
        g.drawLine(x, y, x + 200, y + 200);
        g.drawLine(x + 200, y, x, y + 200);
    }

    private void drawO(Graphics g, int x, int y) {
        g.setColor(Color.BLUE);
        g.drawOval(x, y, 200, 200);
    }

    private void drawGameOver(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 40));

        if (xWin) {
            g.drawString("X wins!", 250, 300);
        } else if (oWin) {
            g.drawString("O wins!", 250, 300);
        } else {
            g.drawString("It's a draw!", 220, 300);
        }
    }

    private void checkGameOver() {
        if (xWin || oWin || isBoardFull()) {
            gameOver = true;
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '\0') {
                    return false;
                }
            }
        }
        return true;
    }

    private void checkWin() {
        checkRows();
        checkCols();
        checkDiagonals();

    }

    public void checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '\0') {
                if (board[i][0] == 'X') {
                    xWin = true;
                } else {
                    oWin = true;
                }
            }
        }
    }

    public void checkCols() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != '\0') {
                if (board[0][i] == 'X') {
                    xWin = true;
                } else {
                    oWin = true;
                }
            }
        }
    }

    public void checkDiagonals() {
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '\0') {
            if (board[0][0] == 'X') {
                xWin = true;
            } else {
                oWin = true;
            }
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '\0') {
            if (board[0][2] == 'X') {
                xWin = true;
            } else {
                oWin = true;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!gameOver) {
            int mouseX = e.getX();
            int mouseY = e.getY();

            int row = mouseY / 200;
            int col = mouseX / 200;

            if (board[row][col] == '\0') {
                board[row][col] = xTurn ? 'X' : 'O';
                xTurn = !xTurn;
                checkWin();
                checkGameOver();
                repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
