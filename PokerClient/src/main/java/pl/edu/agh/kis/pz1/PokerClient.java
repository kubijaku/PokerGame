package pl.edu.agh.kis.pz1;


import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * to be made
 */
public class PokerClient {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 1223;


    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);

        ServerHandler serverConn = new ServerHandler(socket);


        BufferedReader keyboard = new BufferedReader( new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        new Thread(serverConn).start();
        System.out.println("Wait for your turn :) ");
        while (true)
        {
            System.out.println("> ");
            String command  = keyboard.readLine();

            if (command.equals("quit")) break;

            out.println(command);
        }



        socket.close();
        System.exit(0);

    }
}