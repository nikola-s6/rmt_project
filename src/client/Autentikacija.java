package client;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Autentikacija extends JFrame {
    private JPanel panel1;
    private JLabel lbLabela;
    private JButton btnSubmit;
    private JTextField textField1;

    //private Socket cSocket;
    private DataInputStream in;
    private DataOutputStream out;

    private String odgVerifikacije;
    private String sirinaS;
    private String visinaS;


    public Autentikacija(Socket cSocket) {
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.cSocket = cSocket;
        setTitle("Autentikacija");
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(300, 300);
        setResizable(false);
        btnSubmit.addActionListener(e -> {
            String sifra = textField1.getText();
            try {
                out = new DataOutputStream(cSocket.getOutputStream());
                in = new DataInputStream(cSocket.getInputStream());

                out.writeUTF(sifra);
                odgVerifikacije = in.readUTF();

                if (odgVerifikacije.equals("tacno")) {
                    try {
                        sirinaS = in.readUTF();
                        visinaS = in.readUTF();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    new PravljenjeOkvira(cSocket, sirinaS, visinaS);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Pogresna sifra", "Greska", JOptionPane.ERROR_MESSAGE);
                    new Autentikacija(cSocket);
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });


    }


}
