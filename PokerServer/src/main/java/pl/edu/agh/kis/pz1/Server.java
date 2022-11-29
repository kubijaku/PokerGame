package pl.edu.agh.kis.pz1;


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
    private static final int PORT = 1223;
    private static int numberOfPlayers = 2;
    private static String[] names = {"Player1","Player2","Player3","Player4"};
    private static int namesIterator = -1;
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ArrayList<Player> allPlayers = new ArrayList<>(numberOfPlayers);
    private static ExecutorService pool = Executors.newFixedThreadPool(numberOfPlayers);
    private static Deck pokerDeck = new Deck();
    private static ArrayList<Variants> playerVariants = new ArrayList<>();


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

            if(clients.size() == numberOfPlayers )
            {
                pokerDeck.shuffle();

                for( int i=0; i<numberOfPlayers; i++)
                {
                    clients.get(i).out.println("Your Cards are: ");
                    Player newplayer = new Player();
                    newplayer.playerCards = newplayer.get5Cards(pokerDeck);
                    for( Card plCard : newplayer.playerCards)
                    {
                        clients.get(i).out.print(plCard.rank);
                        clients.get(i).out.println(plCard.suit);
                    }

                    int numberOfChanges = 0;
                    while (true){
                        clients.get(i).out.println("Type 'change card' to change one of your cards or 'ok' to confirm no changes");
                        String request = clients.get(i).in.readLine();
                        if (request.contains("change") && numberOfChanges<4) {
                            while (true) {
                                clients.get(i).out.println("Type card index from 0 to 4");
                                request = clients.get(i).in.readLine();
                                if (request.contains("0")) {
                                    newplayer.playerCards = newplayer.changeCard(0, newplayer.playerCards, pokerDeck);
                                    numberOfChanges = numberOfChanges+1;
                                    clients.get(i).out.println("I changed card number 0 ");
                                    break;
                                } else
                                if (request.contains("1")) {
                                    newplayer.playerCards = newplayer.changeCard(1, newplayer.playerCards, pokerDeck);
                                    numberOfChanges = numberOfChanges+1;
                                    clients.get(i).out.println("I changed card number 1  ");
                                    break;
                                }else
                                if (request.contains("2")) {
                                    newplayer.playerCards = newplayer.changeCard(2, newplayer.playerCards, pokerDeck);
                                    numberOfChanges = numberOfChanges+1;
                                    clients.get(i).out.println("I changed card number 2 ");
                                    break;
                                }else
                                if (request.contains("3")) {
                                    newplayer.playerCards = newplayer.changeCard(3, newplayer.playerCards, pokerDeck);
                                    numberOfChanges = numberOfChanges+1;
                                    clients.get(i).out.println("I changed card number 3 ");
                                    break;
                                } else
                                if (request.contains("4")) {
                                    newplayer.playerCards = newplayer.changeCard(4, newplayer.playerCards, pokerDeck);
                                    numberOfChanges = numberOfChanges+1;
                                    clients.get(i).out.println("I changed card number 4 ");
                                    break;
                                }
                                if (request.contains("ok") || numberOfChanges == 4) break;
                            }
                        } else if (request.contains("ok") || numberOfChanges == 4)
                        {
                            break;
                        }
                        for( Card plCard : newplayer.playerCards)
                        {
                            clients.get(i).out.print(plCard.rank);
                            clients.get(i).out.println(plCard.suit);
                        }

                    }
                    playerVariants.add(newplayer.HIGHestVariant(newplayer.playerCards));
                    clients.get(i).out.println(playerVariants.get(i));
                }
                int winnerIndex = getWinnerIndex(playerVariants);
                for( int i=0; i<numberOfPlayers; i++)
                {
                    if( i != winnerIndex) clients.get(i).out.println("You Lost ;( ");
                    else clients.get(i).out.println("You WON ;) ");
                }
                break;
            }

        }
        listener.close();


//        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
//        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//
    }

    private static int getWinnerIndex(ArrayList<Variants> allPlayersVariants) {
        int index = 0;
        boolean isWinner = false;
        for ( int i=0; i<allPlayersVariants.size(); i=i+1)
        {
            int indexOrdinal = allPlayersVariants.get(index).ordinal();
            int iOrdinal = allPlayersVariants.get(i).ordinal();
            if( indexOrdinal > iOrdinal )
            {
                index = i;
                isWinner = true;
            }
        }
        if(isWinner) return index;
        return index;
    }

    public static String getName(ClientHandler client) {
        return names[clients.indexOf(client)];
    }


    public static Deck getDeck() {
        return pokerDeck;
    }
}