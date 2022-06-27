package client;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class PravljenjeOkvira extends Thread {
    private final String sirinaS;
    private final String visinaS;

    private final JFrame frame;
    private final JDesktopPane desktop;
    private final JInternalFrame iFrame;
    private final JPanel cPanel;
    private final Socket cSocket;

    public PravljenjeOkvira(Socket cSocket, String sirinaS, String visinaS) {
        frame = new JFrame();
        desktop = new JDesktopPane();
        iFrame = new JInternalFrame("Server Screen", true, true, true);
        cPanel = new JPanel();
        this.cSocket = cSocket;
        this.sirinaS = sirinaS;
        this.visinaS = visinaS;
        start();
    }

    public void drawGUI() {
        frame.add(desktop, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        iFrame.setLayout(new BorderLayout());
        iFrame.getContentPane().add(cPanel, BorderLayout.CENTER);
        iFrame.setSize(100, 100);
        desktop.add(iFrame);

        try {
            iFrame.setMaximum(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        cPanel.setFocusable(true);
        iFrame.setVisible(true);
    }

    public void run() {
        InputStream in;
        drawGUI();

        try {
            in = cSocket.getInputStream();
            new PrimanjeSlike(in, cPanel, cSocket);
            new SlanjeNaredbi(cSocket, cPanel, sirinaS, visinaS);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
