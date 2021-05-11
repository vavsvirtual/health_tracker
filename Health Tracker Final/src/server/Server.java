package server;

//Import statements
//Communication & File saving
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
//Data structure
import java.util.HashMap;

/**
 * Project      : health_tracker
 * File         : Server.java
 * Last Edit    : 09/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : Handles connections to clients and IO to disk for the
 *                  user database
 */

public class Server {
    //Port & Account HashMap
    public static final int PORT = 3000;
    public static HashMap<String, Account> Accounts = new HashMap<>();

    public static void main(String[] args){
        ServerSocket serverSocket = null;
        Socket clientSocket;
        //Attempt to load Accounts
        try {
            FileInputStream fileIn = new FileInputStream("./server_data/Accounts.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Accounts = (HashMap<String, Account>) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Loaded Accounts");
        } catch (IOException | ClassNotFoundException exception) {
            //If fail to load accounts, create new database
            exception.printStackTrace();
            System.out.println("Cant load Accounts, Creating new Database");
            saveAccounts();
        }
        //Attempt to start server connection
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException exception) {
            System.out.println("Cannot Create ServerSocket: " + exception.getMessage());
            System.exit(1);
        }
        System.out.println("Successfully made ServerSocket");
        //DEBUG:
        for(String string : Accounts.keySet()){
            System.out.print(string + ", ");
        }
        System.out.println("\b\b");


        //Handle Client connections
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Success! Client connected");
                new ClientThread(clientSocket).start();
            } catch (IOException exception) {
                System.out.println("Cannot accept socket connection: " + exception.getMessage());
            }
        }
    }
    //Method for ClientThreads to add newly registered accounts to the database
    public static synchronized boolean addUser(String userName, Account account){
        //If account with same username doesnt already exists
        userName = userName.toLowerCase();
        if(Accounts.get(userName) != null){
            return false;
        }
        //Add account & save to database
        Accounts.put(userName, account);
        //If cant save remove account again and return false
        if(!saveAccounts()){
            Accounts.remove(userName);
            return false;
        }
        return true;
    }
    //Save account method
    private static boolean saveAccounts(){
        try {
            //Open file
            FileOutputStream fileOut = new FileOutputStream("./server_data/Accounts.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            //Write accounts & close file
            out.writeObject(Accounts);
            out.close();
            fileOut.close();
            System.out.println("Updated User Database");
        } catch (IOException exception) {
            //If cant save accounts, return failure
            exception.printStackTrace();
            System.out.println("Cannot save accounts");
            return false;
        }
        return true;
    }
    //Test harness not required
}
