/*
 *
 */
class HumanCS {
    private final char DOT;

    HumanCS(char ch) { DOT = ch; }

    void turn(FieldC fieldc) {
        int x, y;
        do {
            x = random.nextInt(fieldc.getSize());
            y = random.nextInt(fieldc.getSize());
        } while (!fieldc.isCellEmpty(x, y)); //пока незаполнится
        fieldc.setDot(x, y, DOT);//запуск метода из фиелд
    }
}