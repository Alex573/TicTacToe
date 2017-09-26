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
import java.net.ServerSocket;
import java.net.Socket;


import static java.lang.Integer.parseInt;

public class TicTacToeS extends JFrame {

    final String TITLE_OF_PROGRAM = "Tic Tac Toe Server";
    final int WINDOW_SIZE = 330;
    final int WINDOW_DX = 7;
    final int WINDOW_DY = 55;
    final int FIELD_SIZE = 3;
    final int CELL_SIZE = WINDOW_SIZE / FIELD_SIZE;
    final String BTN_INIT = "New game";
    final String BTN_EXIT = "Exit";
    final int SERVER_PORT = 2050; // servet port
    final String SERVER_ADDR = "localhost";
    final String NEW_GAME = "ClIENT NEW GAME";
    final String EXIT_GAMEC = "ClIENT EXIT GAME";

    Canvass canvass = new Canvass();
    FieldS fields = new FieldS(FIELD_SIZE, CELL_SIZE);
    HumanSS humanss = new HumanSS(fields.getHumansDot());
    HumanSC humansc = new HumanSC(fields.getHumancDot());

    ServerSocket server;
    BufferedReader reader;
    static PrintWriter writer;
    Socket socket;
    String message;

    public static void main(String args[]) {
        new TicTacToeS();
    }

    TicTacToeS() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_SIZE + WINDOW_DX, WINDOW_SIZE + WINDOW_DY);
        setLocationRelativeTo(null);
        setResizable(false);

        canvass.setBackground(Color.white);
        canvass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if(FieldS.flag){
                    try {
                        humanss.turn(e.getX()/CELL_SIZE, e.getY()/CELL_SIZE, fields);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(
                                TicTacToeS.this, "Client NEPODKLICHILSI");
                    }
                    canvass.repaint();
                }else {
                    JOptionPane.showMessageDialog(
                            TicTacToeS.this, "Client eshe neschodil");
                }

                canvass.repaint();
                if (fields.isGameOver())
                    JOptionPane.showMessageDialog(
                        TicTacToeS.this, fields.getGameOverMsg());
            }
        });
        JButton init = new JButton(BTN_INIT);
        init.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fields.init();
                canvass.repaint();
                try {
                    writer.println("new");
                    writer.flush();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(
                            TicTacToeS.this, "Client NEPODKLICHILSI");
                }
                FieldS.flag = true;
            }
        });
        JButton exit = new JButton(BTN_EXIT);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    writer.println("exit");
                    writer.flush();
                    socket.close();
                    server.close();

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
        add(canvass, BorderLayout.CENTER);
        setVisible(true);

        try {
            server = new ServerSocket(SERVER_PORT);
            System.out.println("Server started.");
            while (true) {
                socket = server.accept();
                writer = new PrintWriter(socket.getOutputStream());
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                new Thread(new Listener()).start();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally {
            try {

                socket.close();
                System.out.println("Server stop");
                server.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    class Canvass extends JPanel { // for painting
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            fields.paint(g);
        }
    }

    public class Listener implements Runnable {

        @Override
        public void run() {
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.print(message);
                    switch (message) {
                        case "new":
                            JOptionPane.showMessageDialog(
                                    TicTacToeS.this, NEW_GAME);
                            fields.init();
                            canvass.repaint();
                            FieldS.flag=true;
                            break;
                        case "exit":
                            JOptionPane.showMessageDialog(
                                    TicTacToeS.this, EXIT_GAMEC);
                            break;
                        default:
                                String[] wds = message.split(" ");
                                int x = parseInt(wds[0]);
                                int y = parseInt(wds[1]);
                                humansc.turn(fields, x, y);
                                canvass.repaint();
                                FieldS.flag = true;
                                if (fields.isGameOver())
                                 JOptionPane.showMessageDialog(
                                        TicTacToeS.this, fields.getGameOverMsg());
                                break;

                    }


                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}