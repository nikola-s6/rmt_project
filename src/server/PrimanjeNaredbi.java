package server;

import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class PrimanjeNaredbi extends Thread {
    private final Socket socket;
    private final Robot robot;

    public PrimanjeNaredbi(Socket socket, Robot robot) {
        this.socket = socket;
        this.robot = robot;
        start();
    }

    public void run() {
        Scanner scanner;
        try {
            scanner = new Scanner(socket.getInputStream());
            while (socket.isConnected()) {
                int komanda = scanner.nextInt();
                switch (komanda) {
                    case -1 -> robot.mousePress(scanner.nextInt());
                    case -2 -> robot.mouseRelease(scanner.nextInt());
                    case -3 -> robot.keyPress(scanner.nextInt());
                    case -4 -> robot.keyRelease(scanner.nextInt());
                    case -5 -> robot.mouseMove(scanner.nextInt(), scanner.nextInt());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
