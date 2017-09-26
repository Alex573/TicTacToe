/*
 *
 */

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

class FieldS {
    private final int FIELD_SIZE;
    private final int CELL_SIZE;
    private final char HUMANS_DOT = 'x';
    private final char HUMANC_DOT = 'o';
    private final char EMPTY_DOT = '.';
    private final String MSG_DRAW = "Draw, sorry...";
    private final String MSG_HUMANS_WON = "YOU WON!";
    private final String MSG_HUMANC_WON = "CLIENT WON!";
    private char[][] fields;
    private String gameOverMsg;
    static boolean flag = true;

    FieldS(int field_size, int cell_size) {
        FIELD_SIZE = field_size;
        CELL_SIZE = cell_size;
        fields = new char[FIELD_SIZE][FIELD_SIZE];
        init();
    }

    void init() {
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++)
                fields[i][j] = EMPTY_DOT;
        gameOverMsg = null;
    }

    int getSize() { return FIELD_SIZE; }

    char getHumansDot() { return HUMANS_DOT; }

    char getHumancDot() { return HUMANC_DOT; }

    boolean isGameOver() { return gameOverMsg != null; }



    String getGameOverMsg() { return gameOverMsg; }

    void setDot(int x, int y, char dot) {
        System.out.println(x+" "+y);// set dot and check fill and win
        fields[x][y] = dot;
        if (isFull())
            gameOverMsg = MSG_DRAW;
        if (isWin(HUMANS_DOT))
            gameOverMsg = MSG_HUMANS_WON;
        if (isWin(HUMANC_DOT))
            gameOverMsg = MSG_HUMANC_WON;
    }

    boolean isCellEmpty(int x, int y) {
        if (x < 0 || y < 0 || x > FIELD_SIZE - 1 || y > FIELD_SIZE - 1) return false;
        if (fields[x][y] == EMPTY_DOT) return true;
        return false;
    }

    boolean isFull() {
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++)
                if (fields[i][j] == EMPTY_DOT) return false;
        return true;
    }

    boolean isWin(char ch) {
        // checking horizontals / verticals
        for (int i = 0; i < FIELD_SIZE; i++)
            if ((fields[i][0] == ch && fields[i][1] == ch && fields[i][2] == ch) ||
                (fields[0][i] == ch && fields[1][i] == ch && fields[2][i] == ch))
                return true;
        // checking diagonals
        if ((fields[0][0] == ch && fields[1][1] == ch && fields[2][2] == ch) ||
            (fields[2][0] == ch && fields[1][1] == ch && fields[0][2] == ch))
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
                if (fields[x][y] == HUMANS_DOT) {
                    g.setColor(Color.blue);
                    g2.draw(new Line2D.Float(x*CELL_SIZE+CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4, (x+1)*CELL_SIZE-CELL_SIZE/4, (y+1)*CELL_SIZE-CELL_SIZE/4));
                    g2.draw(new Line2D.Float(x*CELL_SIZE+CELL_SIZE/4, (y+1)*CELL_SIZE-CELL_SIZE/4, (x+1)*CELL_SIZE-CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4));
                }
                if (fields[x][y] == HUMANC_DOT) {
                    g.setColor(Color.red);
                    g2.draw(new Ellipse2D.Float(x*CELL_SIZE+CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4, CELL_SIZE/2, CELL_SIZE/2));
                }
            }
        }
    }
}