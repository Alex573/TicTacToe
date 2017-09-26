/*
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

class TicTacToeC extends JFrame {

    final String TITLE_OF_PROGRAM = "Tic Tac Toe Client";
    final int WINDOW_SIZE = 330;
    final int WINDOW_DX = 7;
    final int WINDOW_DY = 55;
    final int FIELD_SIZE = 3;
    final int CELL_SIZE = WINDOW_SIZE / FIELD_SIZE;
    final String BTN_INIT = "New game";
    final String BTN_EXIT = "Exit";
    final String SERVER_ADDR = "localhost"; // server net name or "127.0.0.1"
    final int SERVER_PORT = 2050; // servet port
    final String NEW_GAME = "SERVER NEW GAME";
    final String EXIT_GAMEC = "SERVER EXIT GAME";

    Canvasc canvasc = new Canvasc();
    FieldC fieldc = new FieldC(FIELD_SIZE, CELL_SIZE);
    HumanCC humancc = new HumanCC(fieldc.getHumancDot());
    HumanCS humancs = new HumanCS(fieldc.getHumansDot());

    Socket socket;
    static PrintWriter writer;
    BufferedReader reader;
    Scanner scanner;
    String message;

    public static void main(String args[]) {
        TicTacToeC tic = new TicTacToeC(); }

    TicTacToeC() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_SIZE + WINDOW_DX, WINDOW_SIZE + WINDOW_DY);
        setLocationRelativeTo(null); // окно в центре
        setResizable(false);// нельзя изменять размеры окна
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            writer = new PrintWriter(socket.getOutputStream());
            reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            new Thread(new ServerListener()).start();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        canvasc.setBackground(Color.white);
        canvasc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if(FieldC.flag){
                    humancc.turn(e.getX()/CELL_SIZE, e.getY()/CELL_SIZE, fieldc);
                    canvasc.repaint();
                }else {
                    JOptionPane.showMessageDialog(
                            TicTacToeC.this, "EShe server neschodil");
                }
                if (fieldc.isGameOver())
                    JOptionPane.showMessageDialog(
                        TicTacToeC.this, fieldc.getGameOverMsg());
            }
        });
        JButton init = new JButton(BTN_INIT);
        init.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fieldc.init();
                canvasc.repaint();
                writer.println("new");
                writer.flush();
                FieldC.flag = false;
            }
        });
        JButton exit = new JButton(BTN_EXIT);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                writer.println("exit");
                writer.flush();
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            }
        });

        JPanel bp = new JPanel();
        bp.setLayout(new GridLayout()); // for button panel
        bp.add(init);
        bp.add(exit);

        setLayout(new BorderLayout()); // for main window
        add(bp, BorderLayout.SOUTH);
        add(canvasc, BorderLayout.CENTER);
        setVisible(true);

    }

    class Canvasc extends JPanel { // for painting
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            fieldc.paint(g);
        }
    }

    class ServerListener implements Runnable {
        @Override
        public void run() {
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.print(message);
                    switch (message) {
                        case "new":
                            JOptionPane.showMessageDialog(
                                    TicTacToeC.this, NEW_GAME);
                            fieldc.init();
                            canvasc.repaint();

                            break;
                        case "exit":
                            JOptionPane.showMessageDialog(
                                    TicTacToeC.this, EXIT_GAMEC);
                            socket.close();
                            break;
                        default:
                                String[] wds = message.split(" ");
                                int x = parseInt(wds[0]);
                                int y = parseInt(wds[1]);
                                humancs.turn(fieldc, x, y);
                                canvasc.repaint();
                                FieldC.flag=true;
                            if (fieldc.isGameOver())
                                JOptionPane.showMessageDialog(
                                        TicTacToeC.this, fieldc.getGameOverMsg());
                                break;


                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}