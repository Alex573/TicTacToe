/*
 *
 */
class HumanCS {
    private final char DOT;

    HumanCS(char ch) { DOT = ch; }

    void turn(FieldC fieldc,int x,int y) {

        /*do {
            x = random.nextInt(fieldc.getSize());
            y = random.nextInt(fieldc.getSize());
        } while (!fieldc.isCellEmpty(x, y)); //пока незаполнится*/
        if (fieldc.isCellEmpty(x, y)) {
            if (!fieldc.isGameOver()) fieldc.setDot(x, y, DOT); //если не игра закончена устанавливаем координаты
            //if (!fieldc.isGameOver()) humancs.turn(fieldc);//
        }
    }
}