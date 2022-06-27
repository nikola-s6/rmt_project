package server;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SlanjeSlike extends Thread {

    private final Socket socket;
    private final Robot robot;
    private final Rectangle rectangle;
    private OutputStream out;

    public SlanjeSlike(Socket socket, Robot robot, Rectangle rectangle) {
        this.socket = socket;
        this.rectangle = rectangle;
        this.robot = robot;
        start();
    }

    public void run() {
        try {
            out = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (socket.isConnected()) {
            BufferedImage image = robot.createScreenCapture(rectangle);
            try {
                ImageIO.write(image, "jpeg", out);
                Thread.sleep(15);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
