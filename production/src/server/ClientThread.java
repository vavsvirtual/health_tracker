package server;

import shared.Message;
import java.net.*;
import java.io.*;

/**
 * Project      : health_tracker
 * File         : ClientThread.java
 * Last Edit    : 07/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : A single thread that communicates to a live client,
 *                  handles login/registration etc. Destroyed on disconnect
 */

public class ClientThread extends Thread {
    private ObjectOutputStream outStream;
    private ObjectInputStream inStream;

    //Constructor
    public ClientThread(Socket socket){
        try{
            outStream = new ObjectOutputStream(socket.getOutputStream());
            inStream = new ObjectInputStream(socket.getInputStream());
        }catch(IOException exception){
            System.out.println("Exception ClientThread closing");
            exception.printStackTrace();
            return;
        }
        System.out.println("Client created");
    }

    private void processMessage(Message message){
        Account account;
        switch (message.getType()) {
            case SALT_REQUEST -> {
                System.out.println("Salt request for: " + message.getStringMessage()[0].toLowerCase());
                account = Server.Accounts.get(message.getStringMessage()[0].toLowerCase());
                if (account != null) {
                    sendMessage(true, null, new byte[][]{account.getSalt()});
                    System.out.println("Salt request returned: " + true);
                } else {
                    sendMessage(false, null, null);
                    System.out.println("Salt request returned: " + false);
                }
            }
            case LOGIN -> {
                System.out.println("Login request for: " + message.getStringMessage()[0].toLowerCase());
                account = Server.Accounts.get(message.getStringMessage()[0].toLowerCase());
                if (account.checkPassword(message.getByteMessage()[0])) {
                    sendMessage(true, null, null);
                    System.out.println("Login request returned: " + true);
                } else {
                    sendMessage(false, null, null);
                    System.out.println("Login request returned: " + false);
                }
            }
            case REGISTER -> {
                System.out.println("Registration request for: " + message.getStringMessage()[0].toLowerCase());
                String[] strings = message.getStringMessage();
                byte[][] bytes = message.getByteMessage();
                account = new Account(strings[0], bytes[0], bytes[1], strings[1], strings[2]);
                //Checking email
                boolean success = false;
                if(Account.checkEmailFormat(account.getEmail())){
                    success = Server.addUser(account.getUserName(), account);
                }
                sendMessage(success, null, null);
                System.out.println("Registration request returned: " + success);
            }
        }

    }

    public void sendMessage(Boolean success, String[] stringMessage, byte[][] byteMessage){
        try{
            outStream.writeObject(new Message(success,stringMessage,byteMessage));
        }catch(IOException exception){
            exception.printStackTrace();
        }
    }


    public void run() {
        //Wait for message from client
        while(true){
            try{
                Message message = (Message) inStream.readObject();
                processMessage(message);
            }catch(ClassNotFoundException | IOException exception){
                System.out.println("Exception ClientThread closing");
                exception.printStackTrace();
                return;
            }
        }
    }
}