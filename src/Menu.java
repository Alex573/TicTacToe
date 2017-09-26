import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Menu extends JFrame {
    public static void main(String[] args) {

        new Menu();
    }

   Menu() {
        setTitle("Vibor vida igry");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,100);
        setLocationRelativeTo(null); // окно в центре
        setResizable(false);

        JButton odin = new JButton("Igra s Kompom");
        odin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Thread(new zapOdin()).start();

            }
        });
        JButton setiserver = new JButton("Seti Server");
        setiserver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new Thread(new zapServ()).start();

            }
        });
        JButton seticlient = new JButton("Seti Client");
        seticlient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Thread(new zapClient()).start();

            }
        });
        JButton exit = new JButton("Peredumal");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel bp = new JPanel();
        bp.setLayout(new GridLayout());// for button panel
        bp.add(odin);
       bp.add(setiserver);
        bp.add(seticlient);
        bp.add(exit);

        setLayout(new BorderLayout()); // for main window
        add(bp, BorderLayout.CENTER);
        setVisible(true);

    }
    class zapServ implements Runnable{
        @Override
        public void run() {
            new TicTacToeS();
        }
    }
    class zapClient implements Runnable{
        @Override
        public void run() {
            new TicTacToeC();
        }
    }
    class zapOdin implements Runnable{
        @Override
        public void run() {
            new TicTacToe();
        }
    }
}
