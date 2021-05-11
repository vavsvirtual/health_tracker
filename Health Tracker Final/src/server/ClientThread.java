package server;

//Import statements
import shared.Message;
//Communication
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Project      : health_tracker
 * File         : ClientThread.java
 * Last Edit    : 09/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : A single thread that communicates to a live client,
 *                  handles login/registration etc. Destroyed on disconnect
 */

public class ClientThread extends Thread {
    //Input/Output stream variables
    private ObjectOutputStream outStream;
    private ObjectInputStream inStream;

    //Constructor
    public ClientThread(Socket socket){
        //Initialise in/output streams
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

    private void processMessage(Message message) {
        Account account;
        //Decide what to do based on messageType
        switch (message.getType()) {
            case SALT_REQUEST:
                //Search database for username, return salt if it exists
                System.out.println("Salt request for: " + message.getStringMessage()[0].toLowerCase());
                account = Server.Accounts.get(message.getStringMessage()[0].toLowerCase());
                if (account != null) {
                    sendMessage(true, null, new byte[][]{account.getSalt()});
                    System.out.println("Salt request returned: " + true);
                } else {
                    sendMessage(false, null, null);
                    System.out.println("Salt request returned: " + false);
                }
                break;

            case LOGIN:
                //Search database for username
                System.out.println("Login request for: " + message.getStringMessage()[0].toLowerCase());
                account = Server.Accounts.get(message.getStringMessage()[0].toLowerCase());
                //If username exists, check password
                if(account != null){
                    if (account.checkPassword(message.getByteMessage()[0])) {
                        //Returning user data with login success
                        String[] returnString = new String[]{
                                "Login Accepted: Welcome "  + message.getStringMessage()[0],
                                account.getFullName(),
                                account.getEmail(),
                        };
                        sendMessage(true, returnString, null);
                        System.out.println("Login request returned: " + true);
                    } else {
                        sendMessage(false, new String[]{"Login rejected: Password wrong"}, null);
                        System.out.println("Login request returned: " + false);
                    }
                }else{
                    sendMessage(false, new String[]{"Login rejected: No account under that username"}, null);
                }
                break;

            case REGISTER:
                //Create new Account object based on info
                System.out.println("Registration request for: " + message.getStringMessage()[0].toLowerCase());
                String[] strings = message.getStringMessage();
                byte[][] bytes = message.getByteMessage();
                account = new Account(strings[0], bytes[0], bytes[1], strings[1], strings[2]);
                //Check email
                if (!Account.checkEmailFormat(account.getEmail())) {
                    System.out.println("Registration request returned: " + false);
                    sendMessage(false, new String[]{"Registration Rejected: Email format wrong"}, null);
                //Attempt to add to hashmap, if added username isn't already taken
                }else if(Server.addUser(account.getUserName(), account)){
                    System.out.println("Registration request returned: " + true);
                    sendMessage(true, new String[]{"Registration Successful: Please login"}, null);
                //Attempt to add to hashmap, username already taken, inform user
                }else {
                    System.out.println("Registration request returned: " + false);
                    sendMessage(false, new String[]{"Registration Rejected: Username already in use"}, null);
                }
                break;
        }
    }

    //Message sending function
    private void sendMessage(Boolean success, String[] stringMessage, byte[][] byteMessage){
        //Write message object to outstream
        try{
            outStream.writeObject(new Message(success,stringMessage,byteMessage));
        }catch(IOException exception){
            exception.printStackTrace();
        }
    }

    //Main loop
    public void run() {
        //Wait for message from client
        while(true){
            try{
                //When received process request
                Message message = (Message) inStream.readObject();
                processMessage(message);
            }catch(IOException exception){
                //On client disconnect return
                System.out.println("Client disconnected: Closing Thread");
                return;
            }catch(ClassNotFoundException exception){
                //Cant find message class, error
                exception.printStackTrace();
                System.out.println("Can't find message class");
                return;
            }
        }
    }
    //Test harness not required
}