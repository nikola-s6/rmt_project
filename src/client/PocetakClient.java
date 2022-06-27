package client;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class PocetakClient {
    static int port = 1234;

    public void start() {
        String ip = JOptionPane.showInputDialog("Uneti ip adresu servera:").trim();
        System.out.println(ip);
        inicijalizacija(ip, port);
    }

    public void inicijalizacija(String ip, int port) {
        try {
            Socket socket = new Socket(ip, port);
            System.out.println("Konektovanje na server");
            new Autentikacija(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
