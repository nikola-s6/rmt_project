package server;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class InicijalizacijaVeze {
    private ServerSocket ss;
    private DataInputStream in;
    private DataOutputStream out;
    private String sirinaS;
    private String visinaS;

    public InicijalizacijaVeze(int port, String sifra) {
        Rectangle rectangle;
        Robot robot;

        try {
            ss = new ServerSocket(port);
            System.out.println("Cekanje veze");

            GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gDev = gEnv.getDefaultScreenDevice();
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            sirinaS = "" + dim.getWidth();
            visinaS = "" + dim.getHeight();
            rectangle = new Rectangle(dim);
            robot = new Robot(gDev);

            while (true) {
                Socket socket = ss.accept();
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                String sifraClient = in.readUTF();

                if (sifra.equals(sifraClient)) {
                    out.writeUTF("tacno");
                    out.writeUTF(sirinaS);
                    out.writeUTF(visinaS);
                    out.flush();

                    new SlanjeSlike(socket, robot, rectangle);
                    new PrimanjeNaredbi(socket, robot);
                } else {
                    out.writeUTF("netacno");
                    out.flush();
                }
            }

        } catch (IOException | AWTException e) {
            e.printStackTrace();
        }

    }


}
