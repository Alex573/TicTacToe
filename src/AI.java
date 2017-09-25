/*
 *
 */
import java.util.*;

class AI {
    Random random = new Random();
    private final char DOT;

    AI(char ch) {
        DOT = ch;
    }

    void turn(Field field) {//ходы
        int x, y;
        do {
            x = random.nextInt(field.getSize());
            y = random.nextInt(field.getSize());
        } while (!field.isCellEmpty(x, y)); //пока незаполнится
        field.setDot(x, y, DOT);//запуск метода из фиелд
    }
}