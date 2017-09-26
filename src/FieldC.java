/*
 *
 */

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

class FieldC {
    private final int FIELD_SIZE;
    private final int CELL_SIZE;
    private final char HUMANC_DOT = 'x';
    private final char HUMANS_DOT = 'o';
    private final char EMPTY_DOT = '.';
    private final String MSG_DRAW = "Draw, sorry...";
    private final String MSG_HUMANC_WON = "YOU WON!";
    private final String MSG_HUMANS_WON = "SERVER WON!";
    private char[][] fieldc;
    private String gameOverMsg;
    static boolean flag = false;

    FieldC(int field_size, int cell_size) {
        FIELD_SIZE = field_size;
        CELL_SIZE = cell_size;
        fieldc = new char[FIELD_SIZE][FIELD_SIZE];
        init();
    }

    void init() {
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++)
                fieldc[i][j] = EMPTY_DOT;
        gameOverMsg = null;
    }

    int getSize() { return FIELD_SIZE; }

    char getHumancDot() { return HUMANC_DOT; }

    char getHumansDot() { return HUMANS_DOT; }

    boolean isGameOver() { return gameOverMsg != null; }

    String getGameOverMsg() { return gameOverMsg; }

    void setDot(int x, int y, char dot) {
        System.out.println(x+" "+y);// set dot and check fill and win
        fieldc[x][y] = dot;
        if (isFull())
            gameOverMsg = MSG_DRAW;
        if (isWin(HUMANC_DOT))
            gameOverMsg = MSG_HUMANC_WON;
        if (isWin(HUMANS_DOT))
            gameOverMsg = MSG_HUMANS_WON;
    }

    boolean isCellEmpty(int x, int y) {
        if (x < 0 || y < 0 || x > FIELD_SIZE - 1 || y > FIELD_SIZE - 1) return false;
        if (fieldc[x][y] == EMPTY_DOT) return true;
        return false;
    }

    boolean isFull() {
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++)
                if (fieldc[i][j] == EMPTY_DOT) return false;
        return true;
    }

    boolean isWin(char ch) {
        // checking horizontals / verticals
        for (int i = 0; i < FIELD_SIZE; i++)
            if ((fieldc[i][0] == ch && fieldc[i][1] == ch && fieldc[i][2] == ch) ||
                (fieldc[0][i] == ch && fieldc[1][i] == ch && fieldc[2][i] == ch))
                return true;
        // checking diagonals
        if ((fieldc[0][0] == ch && fieldc[1][1] == ch && fieldc[2][2] == ch) ||
            (fieldc[2][0] == ch && fieldc[1][1] == ch && fieldc[0][2] == ch))
            return true;
        return false;
    }

    public void paint(Graphics g) {
        g.setColor(Color.lightGray);
        for (int i = 1; i < FIELD_SIZE; i++) {
            g.drawLine(0, i*CELL_SIZE, FIELD_SIZE*CELL_SIZE, i*CELL_SIZE);
            g.drawLine(i*CELL_SIZE, 0, i*CELL_SIZE, FIELD_SIZE*CELL_SIZE);
        }
        Graphics2D g2 = (Graphics2D) g; // use Graphics2D
        g2.setStroke(new BasicStroke(5));
        for (int y = 0; y < FIELD_SIZE; y++) {
            for (int x = 0; x < FIELD_SIZE; x++) {
                if (fieldc[x][y] == HUMANS_DOT) {
                    g.setColor(Color.blue);
                    g2.draw(new Line2D.Float(x*CELL_SIZE+CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4, (x+1)*CELL_SIZE-CELL_SIZE/4, (y+1)*CELL_SIZE-CELL_SIZE/4));
                    g2.draw(new Line2D.Float(x*CELL_SIZE+CELL_SIZE/4, (y+1)*CELL_SIZE-CELL_SIZE/4, (x+1)*CELL_SIZE-CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4));
                }
                if (fieldc[x][y] == HUMANC_DOT) {
                    g.setColor(Color.red);
                    g2.draw(new Ellipse2D.Float(x*CELL_SIZE+CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4, CELL_SIZE/2, CELL_SIZE/2));
                }
            }
        }
    }
}