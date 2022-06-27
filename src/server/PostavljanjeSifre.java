package server;

import javax.swing.*;


public class PostavljanjeSifre extends JFrame {
    static int port = 1234;
    private JPanel panel1;
    private JLabel lblLabela;
    private JButton btnDugme;
    private JTextField poljeZaUnos;

    private String sifra;

    public PostavljanjeSifre() {
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Postavljenje sifre");

        btnDugme.addActionListener(e -> {
            sifra = poljeZaUnos.getText();
            dispose();
            new InicijalizacijaVeze(port, sifra);
        });
    }
}
