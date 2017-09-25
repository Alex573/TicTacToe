import static java.lang.Integer.parseInt;

/*
 *
 */
class HumanSC {
    private final char DOT;

    HumanSC(char ch) { DOT = ch; }

    void turn(FieldS fields, int x, int y) {
        if (fields.isCellEmpty(x, y)) {
            if (!fields.isGameOver()) fields.setDot(x, y, DOT); //если не игра закончена устанавливаем координаты
            //if (!fieldc.isGameOver()) humancs.turn(fieldc);//
        }
    }
}