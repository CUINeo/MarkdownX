package src.Sockets;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

public class Client {
    public Socket clientSocket = null;

    public Client() { }

    public void SendFile(String ip, String port, String fileContent) {
        try {
            clientSocket = new Socket(ip, Integer.valueOf(port));
            Writer writer = new OutputStreamWriter(clientSocket.getOutputStream());
            BufferedWriter bw = new BufferedWriter(writer);

            bw.write(fileContent);
            bw.write("\r\nABCDEFGHIJK\r\n");

            bw.close();
            writer.close();
        } catch (Exception e) {
            System.out.println("Unconnected.");
        }
    }
}