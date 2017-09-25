/*
 *
 */
class HumanCC {
    private final char DOT;

    HumanCC(char ch) { DOT = ch; }

    void turn(int x, int y, FieldC fieldc, HumanCS humancs) {
        if (fieldc.isCellEmpty(x, y)) {
            if (!fieldc.isGameOver()) fieldc.setDot(x, y, DOT); //если не игра закончена устанавливаем координаты
            if (!fieldc.isGameOver()) humancs.turn(fieldc);//
        }
    }
}