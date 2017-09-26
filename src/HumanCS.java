/*
 *
 */
class HumanCS {
    private final char DOT;

    HumanCS(char ch) { DOT = ch; }

    void turn(FieldC fieldc,int x,int y) {

        if (fieldc.isCellEmpty(x, y)) {
            if (!fieldc.isGameOver()) fieldc.setDot(x, y, DOT);

        }
    }
}