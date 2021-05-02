package sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    //assigning port
    private static final int PORT = 3000;
    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

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
    public static synchronized Boolean writeToDatabase(String toAppend, File file) {
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter(file, true));
            write.write(toAppend + "\n");
            write.close();
            return true;
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
