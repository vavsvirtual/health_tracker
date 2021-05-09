package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * Project      : health_tracker
 * File         : Server.java
 * Last Edit    : 07/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : Handles connections to clients and IO to disk for the
 *                  user database
 */

public class Server {
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
            exception.printStackTrace();
            System.out.println("Cant load Accounts");
            return;
        }
        //Attempt to start server connection
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException exception) {
            System.out.println("Cannot Create ServerSocket: " + exception.getMessage());
            System.exit(1);
        }
        System.out.println("Successfully made ServerSocket");

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
    public static synchronized boolean addUser(String userName, Account account){
        userName = userName.toLowerCase();
        if(Accounts.get(userName) != null){
            return false;
        }
        Accounts.put(userName, account);
        //Save Accounts after update
        try {
            FileOutputStream fileOut = new FileOutputStream("./server_data/Accounts.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Accounts);
            out.close();
            fileOut.close();
            System.out.println("Updated User Database");
        } catch (IOException i) {
            i.printStackTrace();
        }
        return true;
    }
}
