package pocetna;

import client.PocetakClient;
import server.PocetakServer;

import javax.swing.*;


public class Pocetna extends JFrame {
    private JPanel panel1;
    private JButton btnDrugi;
    private JButton btnOvaj;
    private JLabel lblDrugi;
    private JLabel lblOvaj;
    private JLabel lblNaslov;

    public Pocetna() {
        setContentPane(panel1);
        setTitle("Dobrodosli");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        btnDrugi.addActionListener(e -> {
            System.out.println("Povezivanje na drugi racunar");
            dispose();
            new PocetakClient().start();
        });
        btnOvaj.addActionListener(e -> {
            System.out.println("Povezivanje na ovaj racunar");
            dispose();
            new PocetakServer().start();
        });
    }

    public static void main(String[] args) {
        //new Runnable
        SwingUtilities.invokeLater(() -> {
            Pocetna frame = new Pocetna();
            frame.setVisible(true);
        });
    }
}
