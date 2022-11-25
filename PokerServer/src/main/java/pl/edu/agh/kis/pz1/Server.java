package pl.edu.agh.kis.pz1;

import pl.edu.agh.kis.pz1.util.PokerUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * to be continued
 */
public class Server {
    private static final int PORT = 1234;
    private static String[] names = {"Player1","Player2","Player3","Player4"};
    private static int namesIterator = -1;
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(2);


    public static void main(String[] args) throws IOException
    {
        ServerSocket listener = new ServerSocket(PORT);

        while (true)
        {
            System.out.println("SERVER Waiting for clients connecion...");
            Socket client = listener.accept();
            System.out.println("Server connected to client!");
            ClientHandler clientThread = new ClientHandler(client, clients);
            clients.add(clientThread);

            pool.execute(clientThread);
        }


//        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
//        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//
    }

    public static String getName(ClientHandler client) {
        return names[clients.indexOf(client)];
    }


}