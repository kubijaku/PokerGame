package pl.edu.agh.kis.pz1;

import pl.edu.agh.kis.pz1.util.TextUtils;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import static pl.edu.agh.kis.pz1.util.TextUtils.*;

public class ClientHandler implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<ClientHandler> clients;

    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients) throws IOException {
        this.client = clientSocket;
        this.clients = clients;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);

    }

    @Override
    public void run(){
        try {
            String hello = Hello();

//            for( String letter : hello) out.println(letter);
//            out.println("HELLO");
            while (true){
                String request = in.readLine();
                if (request.contains("name")) {
                    out.println(Server.getName(this));
                } else {
                    out.println("Type 'tell me a name' to get a random name");
                }
            }
        } catch (IOException e) {
            System.err.println("IO exception in client handler");
            System.err.println(e.getStackTrace());
        } finally {
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
