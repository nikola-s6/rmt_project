package client;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SlanjeNaredbi implements MouseMotionListener, MouseListener, KeyListener {

    private final Socket cSocket;
    private final JPanel cPanel;
    private PrintWriter writer;
    private final double sirina;
    private final double visina;

    public SlanjeNaredbi(Socket socket, JPanel panel, String sirinaS, String visinaS) {
        this.cSocket = socket;
        this.cPanel = panel;
        sirina = Double.parseDouble(sirinaS.trim());
        visina = Double.parseDouble(visinaS.trim());

        cPanel.addKeyListener(this);
        cPanel.addMouseListener(this);
        cPanel.addMouseMotionListener(this);

        try {
            writer = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        double xScale = sirina / cPanel.getWidth();
        double yScale = visina / cPanel.getHeight();
        writer.println(Komande.MOVE_MOUSE.getSifra());
        writer.println((int) (e.getX() * xScale));
        writer.println((int) (e.getY() * yScale));
        writer.flush();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        writer.println(Komande.PRESS_KEY.getSifra());
        writer.println(e.getKeyCode());
        writer.flush();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        writer.println(Komande.RELEASE_KEY.getSifra());
        writer.println(e.getKeyCode());
        writer.flush();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        writer.println(Komande.PRESS_MOUSE.getSifra());
        int dugme = e.getButton();
        int xDugme = 16;
        if (dugme == 3) {
            xDugme = 4;
        }
        writer.println(xDugme);
        writer.flush();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        writer.println(Komande.RELEASE_MOUSE.getSifra());
        int dugme = e.getButton();
        int xDugme = 16;
        if (dugme == 3) {
            xDugme = 4;
        }
        writer.println(xDugme);
        writer.flush();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
