/*
 *
 */
class HumanSC {
    private final char DOT;

    HumanSC(char ch) { DOT = ch; }

    void turn(FieldS fields) {
        int x, y;
        do {
            x = random.nextInt(fields.getSize());
            y = random.nextInt(fields.getSize());
        } while (!fields.isCellEmpty(x, y)); //пока незаполнится
        fields.setDot(x, y, DOT);//запуск метода из фиелд
    }
}