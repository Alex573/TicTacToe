/*
*
*
*
* */
class HumanSS {
    private final char DOT;

    HumanSS(char ch) { DOT = ch; }

    void turn(int x, int y, FieldS fields, HumanSC humansc) {
        if (fields.isCellEmpty(x, y)) {
            if (!fields.isGameOver()) fields.setDot(x, y, DOT); //если не игра закончена устанавливаем координаты
            if (!fields.isGameOver()) humansc.turn(fields);//
        }
    }
}