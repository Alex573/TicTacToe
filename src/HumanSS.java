

/*
*
*
*
* */
class HumanSS {
    private final char DOT;

    HumanSS(char ch) { DOT = ch; }

    void turn(int x, int y, FieldS fields) {

           if (fields.isCellEmpty(x, y)) {
               if (!fields.isGameOver()) {
                   fields.setDot(x, y, DOT);
                   TicTacToeS.writer.println(x + " " + y);
                   TicTacToeS.writer.flush();
                   FieldS.flag = false;
               }
           }

    }
}