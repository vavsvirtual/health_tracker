package server;

//Import statements
//Communication & File saving
import javafx.util.Pair;

//Email stuff
import javax.mail.*;
import javax.mail.internet.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
//Data structure
import java.util.*;

/**
 * Project      : health_tracker
 * File         : Server.java
 * Last Edit    : 12/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : Handles connections to clients and IO to disk for the
 *                  user database
 */

public class Server {
    //Port & Account HashMap
    public static final int PORT = 3000;
    public static final File ACCOUNTS_FILE_PATH = new File("./server_data/Accounts.ser");
    public static final File GROUPS_FILE_PATH = new File("./server_data/Groups.ser");
    public static HashMap<String, Account> Accounts;
    public static HashMap<String, Pair<Integer, HashSet<String>>> Groups;

    public static void main(String[] args){
        ServerSocket serverSocket = null;
        Socket clientSocket;
        //Attempt to load Accounts
        Accounts = (HashMap<String, Account>)readObject(ACCOUNTS_FILE_PATH);
        if(Accounts == null){
            System.out.println("Cant load Accounts, Creating new Database");
            Accounts = new HashMap<>();
            if(!saveObject(Accounts, ACCOUNTS_FILE_PATH)){
                System.out.println("NO READ/WRITE PERMISSION ERROR");
                return;
            }
        }
        //Attempt to load groups
        Groups = (HashMap<String, Pair<Integer, HashSet<String>>>) readObject(GROUPS_FILE_PATH);
        if(Groups == null){
            System.out.println("Cant load Groups, Creating new Database");
            Groups = new HashMap<>();
            if(!saveObject(Groups, GROUPS_FILE_PATH)){
                System.out.println("NO READ/WRITE PERMISSION ERROR");
                return;
            }
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
        if(!saveObject(Accounts, ACCOUNTS_FILE_PATH)){
            Accounts.remove(userName);
            return false;
        }
        return true;
    }

    public static synchronized boolean addGroup(String groupName, String userName){
        groupName = groupName.toLowerCase();
        //If group with that name already exists return false
        if(Groups.get(groupName) != null){
            return false;
        }
        //Generate a join code
        int joinCode = (int)(Math.random()*10000);
        //Add group and first user
        Groups.put(groupName, new Pair<>(joinCode, new HashSet<>(Collections.singletonList(userName))));
        //Save to database (if cant save remove group & return false)
        if(!saveObject(Groups, GROUPS_FILE_PATH)){
            Groups.remove(groupName);
            return false;
        }
        return true;
    }

    public static synchronized boolean addUserToGroup(String groupName, String userName, int joinCode){
        groupName = groupName.toLowerCase();
        if(Groups.get(groupName) != null && Groups.get(groupName).getKey() == joinCode){
            if(Groups.get(groupName).getValue().add(userName)){
                return saveObject(Groups, GROUPS_FILE_PATH);
            }else{
                return false;
            }
        }
        return false;
    }




    //Email sending function
    //Source & Credit: https://www.javatpoint.com/example-of-sending-email-using-java-mail-api-through-gmail-server
    public static boolean sendEmail(String emailAddress, String contents){
        final String password = "@=_?'*7F+>&S4V1wioJ6Ylrw#jy:1XjK";
        final String sendingAddress = "health.tracker.team4.5@gmail.com";

        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sendingAddress,password);
                    }
                });
        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
            message.setSubject("Health Tracker Email");
            message.setText(contents);
            //send message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            return false;
        }
        return true;
    }


    //Object loader (used for accounts & groups)
    public static Object readObject(File filePath){
        Object object = null;
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            object = in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Loaded From: " + filePath);
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
            System.out.println("FAILED TO LOAD: " + filePath);
        }
        return object;
    }
    //Object Saver (used for accounts & groups)
    public static boolean saveObject(Object object, File filePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(object);
            out.close();
            fileOut.close();
            System.out.println("Saved To: " + filePath);
            return true;
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("FAILED TO SAVE: " + filePath);
            return false;
        }
    }
}
