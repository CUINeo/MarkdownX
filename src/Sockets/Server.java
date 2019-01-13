package src.Sockets;

import java.io.*;
import javax.swing.*;
import java.net.Socket;
import java.net.ServerSocket;

@SuppressWarnings("ALL")
public class Server {
    private static final int PORT = 8000;
    private JTextArea textArea;

    public Server(JTextArea textArea) {
        this.textArea = textArea;
    }

    // Wait for connection(block)
    public void listen() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);

            while (true) {
                Socket client = serverSocket.accept();

                Reader reader = new InputStreamReader(client.getInputStream());
                BufferedReader br = new BufferedReader(reader);

                String temp;
                StringBuilder builder = new StringBuilder();

                // End of file
                while (!(temp = br.readLine()).equals("ABCDEFGHIJK")) {
                    builder.append(temp);
                    builder.append("\n");
                }

                textArea.setText(builder.toString());
            }
        } catch (Exception ignored) { }
    }
}
