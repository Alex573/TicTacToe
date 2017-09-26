/*
 *
 */
class HumanCC {
    private final char DOT;

    HumanCC(char ch) { DOT = ch; }

    void turn(int x, int y, FieldC fieldc) {
        if (fieldc.isCellEmpty(x, y)) {
            if (!fieldc.isGameOver()){
                fieldc.setDot(x, y, DOT);
                TicTacToeC.writer.println(x+" "+y);
                TicTacToeC.writer.flush();
                FieldC.flag = false;
            }

        }
    }
}