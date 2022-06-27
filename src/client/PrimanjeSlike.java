package client;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class PrimanjeSlike extends Thread {

    private final InputStream in;
    private final JPanel cPanel;
    private final Socket cSocket;
    Image image;

    public PrimanjeSlike(InputStream in, JPanel cPanel, Socket cSocket) {
        this.in = in;
        this.cPanel = cPanel;
        this.cSocket = cSocket;
        start();
    }

    public void run() {
        while (cSocket.isConnected()) {
            byte[] bytes = new byte[1024 * 1024];
            int count = 0;

            do {
                try {
                    count += in.read(bytes, count, bytes.length - count);
                } catch (IOException e) {
                    //e.printStackTrace();
                }
            } while (!(count > 4 && bytes[count - 2] == (byte) -1 && bytes[count - 1] == (byte) -39));
            try {
                image = ImageIO.read(new ByteArrayInputStream(bytes));
            } catch (IOException e) {
                e.printStackTrace();
            }
            image = image.getScaledInstance(cPanel.getWidth(), cPanel.getHeight(), Image.SCALE_FAST);
            Graphics graphics = cPanel.getGraphics();
            graphics.drawImage(image, 0, 0, cPanel.getWidth(), cPanel.getHeight(), cPanel);
        }
    }
}
